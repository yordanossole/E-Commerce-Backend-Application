package com.yordanos.dreamShops.service.user;

import com.yordanos.dreamShops.dto.UserDto;
import com.yordanos.dreamShops.exceptions.AlreadyExistsException;
import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.User;
import com.yordanos.dreamShops.repository.CartRepository;
import com.yordanos.dreamShops.repository.UserRepository;
import com.yordanos.dreamShops.request.CreateUserRequest;
import com.yordanos.dreamShops.request.UpdateUserRequest;
import com.yordanos.dreamShops.service.cart.ICartService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements IUserService {
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final PasswordEncoder passwordEncoder;
    private final ICartService cartService;

    @Override
    public User getUserById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public User createUser(CreateUserRequest request) {
        return Optional.of(request)
                .filter(user -> !userRepository.existsByEmail(request.getEmail()))
                .map(req -> {
                    User user = new User();
                    user.setEmail(request.getEmail());
                    user.setPassword(passwordEncoder.encode(request.getPassword()));
                    user.setFirstName(request.getFirstName());
                    user.setLastName(request.getLastName());
                    return userRepository.save(user);
                }).orElseThrow(() -> new AlreadyExistsException("Oops! " + request.getEmail() + " already exists!"));
    }

    @Override
    public User updateUser(UpdateUserRequest request, Long userId) {
        return userRepository.findById(userId).map(existingUser -> {
            existingUser.setFirstName(request.getFirstName());
            existingUser.setLastName(request.getLastName());
            return userRepository.save(existingUser);
        }).orElseThrow(() -> new ResourceNotFoundException("User not found!"));
    }

    @Override
    public void deleteUser(Long userId) {
        userRepository.findById(userId).ifPresentOrElse(userRepository::delete, () -> {
            throw new ResourceNotFoundException("User not found!");
        });
    }

    @Override
    public UserDto convertUserToDto(User user) {
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return userRepository.findByEmail(email);
    }

    @Override
    public Long initializeNewCartForUser(String username) {
        User user = userRepository.findByEmail(username);
        if (user.getCart() == null) {
            return cartService.initializeNewCart(user);
        }
        return user.getCart().getId();
    }

    @Override
    public Long getCartId(String username) {
        User user = userRepository.findByEmail(username);
        if (user.getCart() != null) {
            return user.getCart().getId();
        }
        else {
            throw new ResourceNotFoundException("User cart not found!");
        }
    }

    @Override
    public Long getUserByUsername(String username) {
        return userRepository.findByEmail(username).getId();
    }
}

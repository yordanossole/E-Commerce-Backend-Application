package com.yordanos.dreamShops.service.user;

import com.yordanos.dreamShops.dto.UserDto;
import com.yordanos.dreamShops.model.User;
import com.yordanos.dreamShops.request.CreateUserRequest;
import com.yordanos.dreamShops.request.UpdateUserRequest;

public interface IUserService {
    User getUserById(Long userId);

    User createUser(CreateUserRequest request);

    User updateUser(UpdateUserRequest request, Long userId);

    void deleteUser(Long userId);

    UserDto convertUserToDto(User user);

    User getAuthenticatedUser();
}

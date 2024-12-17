package com.yordanos.dreamShops.service.cart;

import com.yordanos.dreamShops.dto.CartDto;
import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.Cart;
import com.yordanos.dreamShops.model.User;
import com.yordanos.dreamShops.repository.CartRepository;
import com.yordanos.dreamShops.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicLong;

@Service
@RequiredArgsConstructor
public class CartService implements ICartService {
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final AtomicLong cartIdGenerator = new AtomicLong();
    private final ModelMapper modelMapper;

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
//        BigDecimal totalCartPrice = cart.getCartPrice();
//        cart.setCartPrice(totalCartPrice);
//        return cartRepository.save(cart);
        return cart; // I think this is enough, and it is the only needed
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
//        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
//        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalCartPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getCartPrice();
    }

    @Override
    public Long initializeNewCart(User user) {
        Cart cart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        cart.setId(newCartId);
        cart.setUser(user);
        return cartRepository.save(cart).getId();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

    @Override
    public CartDto convertToDto(Cart cart) {
        return modelMapper.map(cart, CartDto.class);
    }
}

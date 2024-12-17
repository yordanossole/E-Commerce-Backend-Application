package com.yordanos.dreamShops.service.cart;

import com.yordanos.dreamShops.dto.CartDto;
import com.yordanos.dreamShops.model.Cart;
import com.yordanos.dreamShops.model.User;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);

    @Transactional
    void clearCart(Long id);

    BigDecimal getTotalCartPrice(Long id);

    Long initializeNewCart(User user);

    Cart getCartByUserId(Long userId);

    CartDto convertToDto(Cart cart);
}

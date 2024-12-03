package com.yordanos.dreamShops.service.cart;

import com.yordanos.dreamShops.model.Cart;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface ICartService {
    Cart getCart(Long id);

    @Transactional
    void clearCart(Long id);

    BigDecimal getTotalPrice(Long id);

    Long initializeNewCart();

    Cart getCartByUserId(Long userId);
}

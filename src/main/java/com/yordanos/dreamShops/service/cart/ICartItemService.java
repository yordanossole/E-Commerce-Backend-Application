package com.yordanos.dreamShops.service.cart;

import com.yordanos.dreamShops.dto.CartItemDto;
import com.yordanos.dreamShops.model.CartItem;

public interface ICartItemService {

    CartItem addItemToCart(Long cartId, Long productId, int quantity);

    void removeItemFromCart(Long cartId, Long productId);

    CartItem getCartItem(Long cartId, Long productId);

    void updateItemQuantity(Long cartId, Long productId, int quantity);

    CartItemDto converToDto(CartItem cartItem);
}

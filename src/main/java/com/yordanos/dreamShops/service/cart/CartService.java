package com.yordanos.dreamShops.service.cart;

import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.Cart;
import com.yordanos.dreamShops.repository.CartRepository;
import com.yordanos.dreamShops.repository.CartItemRepository;
import lombok.RequiredArgsConstructor;
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

    @Override
    public Cart getCart(Long id) {
        Cart cart = cartRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Cart not found"));
//        BigDecimal totalAmount = cart.getTotalAmount();
//        cart.setTotalAmount(totalAmount);
//        return cartRepository.save(cart);
        return cart; // I think this is enough, and it is the only needed
    }

    @Transactional
    @Override
    public void clearCart(Long id) {
        Cart cart = getCart(id);
        cartItemRepository.deleteAllByCartId(id);
        cart.getItems().clear();
        cartRepository.deleteById(id);
    }

    @Override
    public BigDecimal getTotalPrice(Long id) {
        Cart cart = getCart(id);
        return cart.getTotalAmount();
    }

    @Override
    public Long initializeNewCart() {
        Cart cart = new Cart();
        Long newCartId = cartIdGenerator.incrementAndGet();
        cart.setId(newCartId);
        return cartRepository.save(cart).getId();
    }

    @Override
    public Cart getCartByUserId(Long userId) {
        return cartRepository.findByUserId(userId);
    }

//    @Override
//    public CartDto convertToCartDto(Cart cart) {
//        CartDto cartDto = new CartDto();
//
//        cartDto.setCartId(cart.getId());
//        cartDto.setTotalAmount(cart.getTotalAmount());
//        cartDto.setItems(cartItemService.getCovertedCartItemDtos(cart.getItems()));
//        return null;
//    }
}

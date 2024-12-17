package com.yordanos.dreamShops.controller;

import com.yordanos.dreamShops.dto.CartDto;
import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.Cart;
import com.yordanos.dreamShops.response.ApiResponse;
import com.yordanos.dreamShops.service.cart.ICartService;
import com.yordanos.dreamShops.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/carts")
public class CartController {
    private final ICartService cartService;
    private final IUserService userService;

    @GetMapping("/my-cart")
    public ResponseEntity<ApiResponse> getCart(@RequestParam(required = false) Long cartId) {
        try {
            if (cartId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                cartId = userService.getCartId(username);
            }
            Cart cart = cartService.getCart(cartId);
            CartDto cartDto = cartService.convertToDto(cart);
            return ResponseEntity.ok(new ApiResponse("Success", cartDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/my-cart/total-price")
    public ResponseEntity<ApiResponse> getTotalPrice(@RequestParam(required = false) Long cartId) {
        try {
            if (cartId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                cartId = userService.getCartId(username);
            }
            BigDecimal totalPrice = cartService.getTotalCartPrice(cartId);
            return ResponseEntity.ok(new ApiResponse("Total Price", totalPrice));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/my-cart/clear")
    public ResponseEntity<ApiResponse> clearCart(@RequestParam(required = false) Long cartId) {
        try {
            if (cartId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                cartId = userService.getCartId(username);
            }
            cartService.clearCart(cartId);
            return ResponseEntity.ok(new ApiResponse("Clear Cart Success!", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}

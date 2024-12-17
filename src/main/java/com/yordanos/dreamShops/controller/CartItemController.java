package com.yordanos.dreamShops.controller;

import com.yordanos.dreamShops.dto.CartItemDto;
import com.yordanos.dreamShops.exceptions.ResourceNotFoundException;
import com.yordanos.dreamShops.model.CartItem;
import com.yordanos.dreamShops.response.ApiResponse;
import com.yordanos.dreamShops.service.cart.ICartItemService;
import com.yordanos.dreamShops.service.cart.ICartService;
import com.yordanos.dreamShops.service.user.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/cartItems")
public class CartItemController {
    private final ICartItemService cartItemService;
    private final ICartService cartService;
    private final IUserService userService;

    @PostMapping("/item/add")
    public ResponseEntity<ApiResponse> addItemToCart(@RequestParam(required = false) Long cartId,
                                                     @RequestParam Long productId,
                                                     @RequestParam Integer quantity) {
        try {
            if (cartId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                cartId = userService.initializeNewCartForUser(username);
            }
            CartItem cartItem = cartItemService.addItemToCart(cartId, productId, quantity);
            CartItemDto cartItemDto = cartItemService.convertToDto(cartItem);
            return ResponseEntity.ok(new ApiResponse("Add Item Success", cartItemDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @DeleteMapping("/item/{productId}/remove")
    public ResponseEntity<ApiResponse> removeItemFromCart(@RequestParam(required = false) Long cartId, @PathVariable Long productId) {
        try {
            if (cartId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                cartId = userService.getCartId(username);
            }
            cartItemService.removeItemFromCart(cartId, productId);
            return ResponseEntity.ok(new ApiResponse("Remove Item Success", null));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PutMapping("/item/{productId}/update")
    public ResponseEntity<ApiResponse> updateItemQuantity(@RequestParam(required = false) Long cartId,
                                                      @PathVariable Long productId,
                                                      @RequestParam Integer quantity) {
        try {
            if (cartId == null) {
                String username = SecurityContextHolder.getContext().getAuthentication().getName();
                cartId = userService.getCartId(username);
            }
            CartItem cartItem = cartItemService.updateItemQuantity(cartId, productId, quantity);
            CartItemDto cartItemDto = cartItemService.convertToDto(cartItem);
            return ResponseEntity.ok(new ApiResponse("Update Item Success", cartItemDto));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}

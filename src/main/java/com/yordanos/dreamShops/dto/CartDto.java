package com.yordanos.dreamShops.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Set;

@Data
public class CartDto {
    private Long cartId;
    private BigDecimal totalCartPrice;
    private Set<CartItemDto> items;
    private Long userId;
}

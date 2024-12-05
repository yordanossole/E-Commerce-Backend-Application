package com.yordanos.dreamShops.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long orderId; // it was initially 'id'
    private Long userId;
    private LocalDateTime orderDate; // it was initially LocalDate
    private BigDecimal totalAmount;
    private String orderStatus; // I wanted it to be OrderStatus, orderStatus
    private List<OrderItemDto> items;
}

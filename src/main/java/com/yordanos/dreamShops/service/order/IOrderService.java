package com.yordanos.dreamShops.service.order;

import com.yordanos.dreamShops.dto.OrderDto;

import java.util.List;

public interface IOrderService {
    OrderDto placeOrder(Long userId);
    OrderDto getOrder(Long orderId);
    List<OrderDto> getUserOrders(Long userId);
}

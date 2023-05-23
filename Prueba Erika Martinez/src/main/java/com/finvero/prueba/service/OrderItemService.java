package com.finvero.prueba.service;

import com.finvero.prueba.dto.OrderItemDto;
import com.finvero.prueba.model.Order;
import com.finvero.prueba.model.OrderItem;

import java.util.List;
import java.util.Optional;

public interface OrderItemService {
    OrderItem save(OrderItemDto orderItem);
    OrderItem update(int id, OrderItemDto orderItem);
    void deleteById(int id);

    Optional<OrderItem> findById(int id);

    List<OrderItem> findAll();
}

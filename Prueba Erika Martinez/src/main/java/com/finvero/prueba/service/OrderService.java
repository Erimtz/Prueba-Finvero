package com.finvero.prueba.service;

import com.finvero.prueba.dto.OrderDto;
import com.finvero.prueba.dto.ShoppingCartDto;
import com.finvero.prueba.model.Order;

import java.util.List;
import java.util.Optional;

public interface OrderService {
    Order save(OrderDto order);
    Order update(int id, OrderDto order);
    void deleteById(int id);

    Optional<Order> findById(int id);

    List<Order> findAll();

    ShoppingCartDto getShoppingCartDtoByOrderId(int orderId);
}

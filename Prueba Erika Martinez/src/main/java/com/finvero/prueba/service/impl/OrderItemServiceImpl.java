package com.finvero.prueba.service.impl;

import com.finvero.prueba.dto.OrderItemDto;
import com.finvero.prueba.exception.ResourceNotFoundException;
import com.finvero.prueba.model.Order;
import com.finvero.prueba.model.OrderItem;
import com.finvero.prueba.model.Product;
import com.finvero.prueba.repo.OrderItemRepository;
import com.finvero.prueba.service.OrderItemService;
import com.finvero.prueba.service.OrderService;
import com.finvero.prueba.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class OrderItemServiceImpl implements OrderItemService {
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private OrderService orderService;

    @Autowired
    private ProductService  productService;

    @Override
    public OrderItem save(OrderItemDto orderItemDto) {

        Optional<Product> product = productService.findById(orderItemDto.getProductid());
        Optional<Order> order = orderService.findById(orderItemDto.getOrderid());
        OrderItem orderItem = orderItemDto.toEntity();

        if(product.isPresent() && order.isPresent()){
            orderItem.setOrder(order.get());
            orderItem.setProduct(product.get());
            return orderItemRepository.save(orderItem);
        }else {
            throw new ResourceNotFoundException("Product or order not found ");
        }

    }

    @Override
    public OrderItem update(int id, OrderItemDto orderItemDto) {
        Optional<OrderItem> orderItemFound = findById(id);

        if(orderItemFound.isPresent()){
         return   save(orderItemDto);
        }else{
            throw new ResourceNotFoundException("OrderItem not found with ID: " + id);
        }
    }

    @Override
    public void deleteById(int id) {
        Optional<OrderItem> orderItemFound = findById(id);
        if(orderItemFound.isPresent()){
            orderItemRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("OrderItem not found with ID: " + id);
        }

    }

    @Override
    public Optional<OrderItem> findById(int id) {
        return orderItemRepository.findById(id);
    }

    @Override
    public List<OrderItem> findAll() {
        return orderItemRepository.findAll();
    }
}

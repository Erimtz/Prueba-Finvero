package com.finvero.prueba.controller;

import com.finvero.prueba.dto.OrderItemDto;
import com.finvero.prueba.model.OrderItem;
import com.finvero.prueba.service.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orderItems")
public class OrderItemController {
    @Autowired
    private  OrderItemService orderItemService;


    @GetMapping
    public ResponseEntity<List<OrderItem>> getOrderItems() {
        return new ResponseEntity<>(orderItemService.findAll(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OrderItem> createOrderItem(@RequestBody OrderItemDto orderItem) {
        OrderItem createdOrderItem = orderItemService.save(orderItem);
        return new ResponseEntity<>(createdOrderItem, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderItem> getOrderItemById(@PathVariable int id) {
        Optional<OrderItem> orderItem = orderItemService.findById(id);
        if (orderItem.isPresent()) {
            return new ResponseEntity<>(orderItem.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<OrderItem> updateOrderItem(@PathVariable int id, @RequestBody OrderItemDto orderItem) {
        OrderItem updatedOrderItem = orderItemService.update(id, orderItem);
        if (updatedOrderItem != null) {
            return new ResponseEntity<>(updatedOrderItem, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrderItem(@PathVariable int id) {
         orderItemService.deleteById(id);

         return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
}

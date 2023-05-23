package com.finvero.prueba.service.impl;

import com.finvero.prueba.dto.IShoppingCartDto;
import com.finvero.prueba.dto.OrderDto;
import com.finvero.prueba.dto.ShoppingCartDto;
import com.finvero.prueba.exception.ResourceNotFoundException;
import com.finvero.prueba.model.Order;
import com.finvero.prueba.model.OrderItem;
import com.finvero.prueba.model.UserEntity;
import com.finvero.prueba.repo.OrderItemRepository;
import com.finvero.prueba.repo.OrderRepository;
import com.finvero.prueba.repo.UserRepository;
import com.finvero.prueba.service.OrderService;
import com.finvero.prueba.service.UserService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private  OrderItemRepository orderItemRepository;
    @Autowired
    private  UserRepository userRepository;
    @Autowired
    private UserService userService;

    @Override
    public Order save(OrderDto order) {
        Optional<UserEntity> user = userService.findById(order.getUserid());
        Order order1 = order.toEntity();


        if(user.isPresent()){
            order1.setUser(user.get());
            return orderRepository.save(order1);
        }else{
            throw new ResourceNotFoundException("User not found with ID: " + order.getUserid());
        }
    }

    @Override
    public Order update(int id, OrderDto order) {
        Optional<Order> orderFound = findById(id);

        if(orderFound.isPresent()){
            return   save(order);
        }else{
            throw new ResourceNotFoundException("Order not found with ID: " + id);
        }
    }
    @Override
    public void deleteById(int id) {
        Optional<Order> orderFound = findById(id);
        if(orderFound.isPresent()){
            orderRepository.deleteById(id);
        }else{
            throw new ResourceNotFoundException("Order not found with ID: " + id);
        }
    }

    @Override
    public Optional<Order> findById(int id) {
        return orderRepository.findById(id);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    @Override
    // Implementa la lógica para obtener los datos de la consulta
    public ShoppingCartDto getShoppingCartDtoByOrderId(int orderId) {
        // Obtiene la order_entity con el id especificado
        Order orderEntity = orderRepository.findById(orderId).orElse(null);
        if (orderEntity == null) {
            throw new ResourceNotFoundException("No se encontró una order con el ID : " +orderId);
        }

        // Obtiene la suma de precio * cantidad de los orderitem relacionados a la order_entity
        BigDecimal total = orderItemRepository.sumPriceByOrderId(orderId);

        // Obtiene el username del usuario asociado a la order_entity
        String username = userRepository.getUsername(orderId);

        // Crea y devuelve el objeto ShoppingCartDto con los datos obtenidos
        return new ShoppingCartDto(orderEntity.getId(), total, username);
    }
}

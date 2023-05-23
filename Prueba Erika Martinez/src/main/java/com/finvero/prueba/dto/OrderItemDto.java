package com.finvero.prueba.dto;

import com.finvero.prueba.model.OrderItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private int orderid;
    private int productid;
    private int quantity;
    private BigDecimal price;

    public OrderItem toEntity(){
        OrderItem orderItem = new OrderItem();
        orderItem.setPrice(price);
        orderItem.setQuantity(quantity);
        return orderItem;
    }
}

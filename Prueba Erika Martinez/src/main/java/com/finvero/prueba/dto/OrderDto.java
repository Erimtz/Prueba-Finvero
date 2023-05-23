package com.finvero.prueba.dto;

import com.finvero.prueba.model.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private int userid;
    private Date date;
    private String status;

    public Order toEntity(){
        Order order = new Order();
        order.setDate(date);
        order.setStatus(status);
        return order;
    }
}

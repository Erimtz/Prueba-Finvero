package com.finvero.prueba.dto;

import lombok.*;
import org.springframework.web.bind.annotation.GetMapping;

import java.math.BigDecimal;

@Getter
@Setter

public class ShoppingCartDto {
    private int orderId;
    private BigDecimal total;
    private String user;

    public ShoppingCartDto(int orderId, BigDecimal total, String user) {
        this.orderId = orderId;
        this.total = total;
        this.user = user;
    }

    public ShoppingCartDto() {

    }
}

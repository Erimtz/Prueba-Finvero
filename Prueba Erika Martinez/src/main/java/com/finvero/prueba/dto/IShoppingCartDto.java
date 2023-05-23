package com.finvero.prueba.dto;


import java.math.BigDecimal;

public interface IShoppingCartDto {
     int getOrderId();
     BigDecimal getTotal();
     String getUser();

}

package com.prueba.vinestraprueba.entity;

import lombok.AllArgsConstructor;
import lombok.Data;


public class PayOrderEntity {

    private Long id;
    private Long orderId;
    private double amount;

    public PayOrderEntity(Long id, Long orderId, double amount) {
        this.id = id;
        this.orderId = orderId;
        this.amount = amount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}

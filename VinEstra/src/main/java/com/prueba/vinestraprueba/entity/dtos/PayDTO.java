package com.prueba.vinestraprueba.entity.dtos;


public class PayDTO {

    private Long orderId;
    private double amount;

    public PayDTO(Long orderId, double amount) {
        this.orderId = orderId;
        this.amount = amount;
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

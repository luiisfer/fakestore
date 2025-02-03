package com.prueba.vinestraprueba.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;



public class OrderEntity {
    private Long id;
    private Long clientId;
    private Date dateOrder;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Long clientId, Date dateOrder) {
        this.id = id;
        this.clientId = clientId;
        this.dateOrder = dateOrder;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getClientId() {
        return clientId;
    }

    public void setClientId(Long clientId) {
        this.clientId = clientId;
    }

    public Date getDateOrder() {
        return dateOrder;
    }

    public void setDateOrder(Date dateOrder) {
        this.dateOrder = dateOrder;
    }
}

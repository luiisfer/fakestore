package com.prueba.vinestraprueba.service;

import com.prueba.vinestraprueba.entity.OrderEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {

    private final List<OrderEntity> orders = new ArrayList<>();


    public List<OrderEntity> getAllOrders() {
        return orders;
    }
    public Optional<OrderEntity> getOrderById(Long id) {
        return orders.stream()
                .filter(order -> order.getId().equals(id))
                .findFirst();
    }

    public OrderEntity addOrder(OrderEntity order) {
        order.setId(order.getId());
        order.setDateOrder(new Date());
        order.setClientId(order.getClientId());
        orders.add(order);
        return order;
    }

    public boolean deleteOrder(Long id) {
        return orders.removeIf(order -> order.getId().equals(id));
    }



}

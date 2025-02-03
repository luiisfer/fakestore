package com.prueba.vinestraprueba.service;

import com.prueba.vinestraprueba.entity.PayOrderEntity;
import com.prueba.vinestraprueba.entity.dtos.PayDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class PayService {

    private final List<PayOrderEntity> payments = new ArrayList<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public List<PayOrderEntity> getPayments() {
        return payments;
    }


    public void addPayment(PayDTO payDTO) {
        PayOrderEntity pay = new PayOrderEntity(
                idGen.getAndIncrement(),
                payDTO.getOrderId(),
                payDTO.getAmount()
        );
        payments.add(pay);
    }
}

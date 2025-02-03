package com.prueba.vinestraprueba.controller;

import com.prueba.vinestraprueba.entity.PayOrderEntity;
import com.prueba.vinestraprueba.entity.dtos.PayDTO;
import com.prueba.vinestraprueba.service.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    private final PayService payService;

    @Autowired
    public PaymentController(PayService payService) {
        this.payService = payService;
    }

    @GetMapping
    public List<PayOrderEntity> getPayments() {
        return payService.getPayments();
    }

    @PostMapping
    public ResponseEntity<String> addPayment(@RequestBody PayDTO payDTO) {
        payService.addPayment(payDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body("Pago Exitoso");
    }
}

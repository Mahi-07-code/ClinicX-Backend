
package com.example.demo.controller;

import com.example.demo.Payment;
import com.example.demo.service.PaymentService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    // Create payment
    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestBody Payment payment) {
        return ResponseEntity.ok(
            paymentService.createPayment(payment)
        );
    }

    // Get all payments
    @GetMapping
    public List<Payment> getAllPayments() {
        return paymentService.getAllPayments();
    }

    // Get payments by clinic
    @GetMapping("/clinic/{clinicId}")
    public List<Payment> getPaymentsByClinic(
            @PathVariable String clinicId) {
        return paymentService.getPaymentsByClinic(clinicId);
    }

    // Get payments by subscription
    @GetMapping("/subscription/{subscriptionId}")
    public List<Payment> getPaymentsBySubscription(
            @PathVariable String subscriptionId) {
        return paymentService.getPaymentsBySubscription(subscriptionId);
    }
}
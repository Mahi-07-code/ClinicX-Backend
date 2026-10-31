
package com.example.demo.service;

import com.example.demo.Payment;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.SubscriptionRepository;
import com.example.demo.Subscription;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final SubscriptionRepository subscriptionRepository;

    public PaymentService(
            PaymentRepository paymentRepository,
            SubscriptionRepository subscriptionRepository) {
        this.paymentRepository = paymentRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    public Payment createPayment(Payment payment) {

        Subscription subscription = subscriptionRepository
                .findById(payment.getSubscriptionId())
                .orElseThrow(() ->
                    new IllegalArgumentException("Subscription not found"));

        if (!subscription.getClinicId().equals(payment.getClinicId())) {
            throw new IllegalArgumentException(
                "Subscription does not belong to this clinic");
        }

        if (payment.getAmount() != subscription.getTotalAmount()) {
            throw new IllegalArgumentException(
                "Payment amount does not match subscription amount");
        }

        String method = payment.getPaymentMethod();

        if (method == null ||
            !(method.equalsIgnoreCase("UPI") ||
              method.equalsIgnoreCase("Credit Card") ||
              method.equalsIgnoreCase("Debit Card") ||
              method.equalsIgnoreCase("Internet Banking"))) {

            throw new IllegalArgumentException("Invalid payment method");
        }

        payment.setPaymentMethod(method);
        payment.setTransactionId("DEMO-" + UUID.randomUUID());
        payment.setStatus("Successful");

        return paymentRepository.save(payment);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    public List<Payment> getPaymentsByClinic(String clinicId) {
        return paymentRepository.findByClinicId(clinicId);
    }

    public List<Payment> getPaymentsBySubscription(String subscriptionId) {
        return paymentRepository.findBySubscriptionId(subscriptionId);
    }
}
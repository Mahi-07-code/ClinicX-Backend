
package com.example.demo.service;

import com.example.demo.Subscription;
import com.example.demo.repository.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {

    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionService(SubscriptionRepository subscriptionRepository) {
        this.subscriptionRepository = subscriptionRepository;
    }

    public Subscription createSubscription(Subscription subscription) {

        int baseDoctors;
        double basePrice;

        if ("6 Months".equalsIgnoreCase(subscription.getPlanName())) {
            baseDoctors = 2;
            basePrice = 5000;
        } else if ("1 Year".equalsIgnoreCase(subscription.getPlanName())) {
            baseDoctors = 4;
            basePrice = 10000;
        } else {
            throw new IllegalArgumentException("Invalid subscription plan");
        }

        if (subscription.getAdditionalDoctors() < 0) {
            throw new IllegalArgumentException("Additional doctors cannot be negative");
        }

        subscription.setDoctorLimit(baseDoctors);
        subscription.setTotalAmount(
            basePrice + (subscription.getAdditionalDoctors() * 500)
        );

        subscription.setStatus("Active");

        return subscriptionRepository.save(subscription);
    }

    public List<Subscription> getAllSubscriptions() {
        return subscriptionRepository.findAll();
    }

    public List<Subscription> getSubscriptionsByClinic(String clinicId) {
        return subscriptionRepository.findByClinicId(clinicId);
    }
}

package com.example.demo.controller;

import com.example.demo.Subscription;
import com.example.demo.service.SubscriptionService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subscriptions")
public class SubscriptionController {

    private final SubscriptionService subscriptionService;

    public SubscriptionController(SubscriptionService subscriptionService) {
        this.subscriptionService = subscriptionService;
    }

    // Create subscription
    @PostMapping
    public ResponseEntity<Subscription> createSubscription(
            @RequestBody Subscription subscription) {
        return ResponseEntity.ok(
            subscriptionService.createSubscription(subscription)
        );
    }

    // Get all subscriptions
    @GetMapping
    public List<Subscription> getAllSubscriptions() {
        return subscriptionService.getAllSubscriptions();
    }

    // Get subscriptions by clinic
    @GetMapping("/clinic/{clinicId}")
    public List<Subscription> getSubscriptionsByClinic(
            @PathVariable String clinicId) {
        return subscriptionService.getSubscriptionsByClinic(clinicId);
    }
}

package com.example.demo.repository;

import com.example.demo.Payment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface PaymentRepository extends MongoRepository<Payment, String> {

    List<Payment> findByClinicId(String clinicId);

    List<Payment> findBySubscriptionId(String subscriptionId);
}
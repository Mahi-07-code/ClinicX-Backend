
package com.example.demo.repository;

import com.example.demo.Subscription;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface SubscriptionRepository extends MongoRepository<Subscription, String> {

    List<Subscription> findByClinicId(String clinicId);

    Optional<Subscription> findFirstByClinicIdOrderByStartDateDesc(String clinicId);
}
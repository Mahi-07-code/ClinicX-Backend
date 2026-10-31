
package com.example.demo.repository;

import com.example.demo.Clinic;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ClinicRepository extends MongoRepository<Clinic, String> {

    Optional<Clinic> findByPhone(String phone);

}
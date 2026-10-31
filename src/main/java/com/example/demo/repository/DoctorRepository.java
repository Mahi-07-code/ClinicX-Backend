package com.example.demo.repository;

import com.example.demo.Doctor;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface DoctorRepository extends MongoRepository<Doctor, String> {

    List<Doctor> findByClinicId(String clinicId);

    Optional<Doctor> findByPhone(String phone);
}
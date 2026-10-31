
package com.example.demo.repository;

import com.example.demo.Patient;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface PatientRepository extends MongoRepository<Patient, String> {

    List<Patient> findByClinicId(String clinicId);

    Optional<Patient> findByClinicIdAndPhone(String clinicId, String phone);
}

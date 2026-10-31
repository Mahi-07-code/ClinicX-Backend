
package com.example.demo.repository;

import com.example.demo.Consultation;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ConsultationRepository
        extends MongoRepository<Consultation, String> {

    List<Consultation> findByPatientId(String patientId);

    List<Consultation> findByDoctorId(String doctorId);
}


package com.example.demo.service;

import com.example.demo.Patient;
import com.example.demo.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    public Patient registerPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public List<Patient> getPatientsByClinic(String clinicId) {
        return patientRepository.findByClinicId(clinicId);
    }

    public Optional<Patient> findPatientByPhone(String clinicId, String phone) {
        return patientRepository.findByClinicIdAndPhone(clinicId, phone);
    }
}

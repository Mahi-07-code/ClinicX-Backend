
package com.example.demo.service;

import com.example.demo.Consultation;
import com.example.demo.repository.ConsultationRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class ConsultationService {

    private final ConsultationRepository consultationRepository;

    public ConsultationService(
            ConsultationRepository consultationRepository) {
        this.consultationRepository = consultationRepository;
    }

    // Save consultation
    public Consultation saveConsultation(Consultation consultation) {
        return consultationRepository.save(consultation);
    }

    // Get all consultations
    public List<Consultation> getAllConsultations() {
        return consultationRepository.findAll();
    }

    // Get consultations by patient
    public List<Consultation> getConsultationsByPatient(String patientId) {
        return consultationRepository.findByPatientId(patientId);
    }

    // Get consultations by doctor
    public List<Consultation> getConsultationsByDoctor(String doctorId) {
        return consultationRepository.findByDoctorId(doctorId);
    }

    // Update consultation
    public Consultation updateConsultation(
            String id, Consultation updatedConsultation) {

        Consultation existingConsultation =
                consultationRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Consultation not found"
                ));

        existingConsultation.setNotes(updatedConsultation.getNotes());

        existingConsultation.setPrescription(
                updatedConsultation.getPrescription()
        );

        existingConsultation.setConsultationDate(
                updatedConsultation.getConsultationDate()
        );

        existingConsultation.setFollowUpDate(
                updatedConsultation.getFollowUpDate()
        );

        return consultationRepository.save(existingConsultation);
    }
}
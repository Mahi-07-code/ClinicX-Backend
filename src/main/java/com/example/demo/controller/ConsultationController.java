
package com.example.demo.controller;

import com.example.demo.Consultation;
import com.example.demo.service.ConsultationService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consultations")
@CrossOrigin(origins = "*")
public class ConsultationController {

    private final ConsultationService consultationService;

    public ConsultationController(
            ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

    // Save consultation
    @PostMapping
    public Consultation saveConsultation(
            @RequestBody Consultation consultation) {

        return consultationService.saveConsultation(consultation);
    }

    // Get all consultations
    @GetMapping
    public List<Consultation> getAllConsultations() {
        return consultationService.getAllConsultations();
    }

    // Get consultations by patient
    @GetMapping("/patient/{patientId}")
    public List<Consultation> getConsultationsByPatient(
            @PathVariable String patientId) {

        return consultationService.getConsultationsByPatient(patientId);
    }

    // Get consultations by doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Consultation> getConsultationsByDoctor(
            @PathVariable String doctorId) {

        return consultationService.getConsultationsByDoctor(doctorId);
    }

    // Update consultation
    @PutMapping("/{id}")
    public Consultation updateConsultation(
            @PathVariable String id,
            @RequestBody Consultation consultation) {

        return consultationService.updateConsultation(id, consultation);
    }
}
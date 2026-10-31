
package com.example.demo.controller;

import com.example.demo.Patient;
import com.example.demo.service.PatientService;

import java.util.List;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
@CrossOrigin(origins = "*")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping
    public Patient registerPatient(@RequestBody Patient patient) {
        return patientService.registerPatient(patient);
    }

    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    @GetMapping("/clinic/{clinicId}")
    public List<Patient> getPatientsByClinic(
            @PathVariable String clinicId) {
        return patientService.getPatientsByClinic(clinicId);
    }

    @GetMapping("/clinic/{clinicId}/phone/{phone}")
    public Optional<Patient> findPatientByPhone(
            @PathVariable String clinicId,
            @PathVariable String phone) {
        return patientService.findPatientByPhone(clinicId, phone);
    }
}

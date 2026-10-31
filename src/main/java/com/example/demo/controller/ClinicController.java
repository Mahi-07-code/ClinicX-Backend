
package com.example.demo.controller;

import com.example.demo.Clinic;
import com.example.demo.service.ClinicService;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/clinics")
@CrossOrigin(origins = "*")
public class ClinicController {

    private final ClinicService clinicService;

    public ClinicController(ClinicService clinicService) {
        this.clinicService = clinicService;
    }

    // Register clinic
    @PostMapping
    public Clinic registerClinic(@RequestBody Clinic clinic) {
        return clinicService.registerClinic(clinic);
    }

    // Get all clinics
    @GetMapping
    public List<Clinic> getAllClinics() {
        return clinicService.getAllClinics();
    }

    // Search clinics
    @GetMapping("/search")
    public List<Clinic> searchClinics(
            @RequestParam(required = false) String keyword,
            @RequestParam(required = false) String status) {

        return clinicService.searchClinics(keyword, status);
    }

    // Get clinic by ID
    @GetMapping("/{id}")
    public Optional<Clinic> getClinicById(@PathVariable String id) {
        return clinicService.getClinicById(id);
    }

    // Update clinic profile
    @PutMapping("/{id}")
    public Clinic updateClinic(
            @PathVariable String id,
            @RequestBody Clinic clinic) {

        return clinicService.updateClinic(id, clinic);
    }

    // Update clinic status
    @PatchMapping("/{id}/status")
    public Clinic updateClinicStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");

        return clinicService.updateClinicStatus(id, status);
    }
}

package com.example.demo.controller;

import com.example.demo.Doctor;
import com.example.demo.service.DoctorService;

import java.util.List;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/doctors")
@CrossOrigin(origins = "*")
public class DoctorController {

    private final DoctorService doctorService;

    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    // Add doctor
    @PostMapping
    public Doctor addDoctor(@RequestBody Doctor doctor) {
        return doctorService.addDoctor(doctor);
    }

    // Get all doctors
    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorService.getAllDoctors();
    }

    // Get doctors by clinic
    @GetMapping("/clinic/{clinicId}")
    public List<Doctor> getDoctorsByClinic(
            @PathVariable String clinicId) {
        return doctorService.getDoctorsByClinic(clinicId);
    }

    // Get doctor by ID
    @GetMapping("/{id}")
    public Doctor getDoctorById(@PathVariable String id) {
        return doctorService.getDoctorById(id);
    }

    // Update doctor profile
    @PutMapping("/{id}")
    public Doctor updateDoctor(
            @PathVariable String id,
            @RequestBody Doctor doctor) {
        return doctorService.updateDoctor(id, doctor);
    }
}
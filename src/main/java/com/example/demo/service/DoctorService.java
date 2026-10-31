
package com.example.demo.service;

import com.example.demo.Doctor;
import com.example.demo.repository.DoctorRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;

@Service
public class DoctorService {

    private final DoctorRepository doctorRepository;

    public DoctorService(DoctorRepository doctorRepository) {
        this.doctorRepository = doctorRepository;
    }

    // Add doctor
    public Doctor addDoctor(Doctor doctor) {
        doctor.setStatus("Active");
        return doctorRepository.save(doctor);
    }

    // Get all doctors
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    // Get doctors by clinic
    public List<Doctor> getDoctorsByClinic(String clinicId) {
        return doctorRepository.findByClinicId(clinicId);
    }

    // Get doctor by ID
    public Doctor getDoctorById(String id) {
        return doctorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Doctor not found"
                ));
    }

    // Update doctor profile
    public Doctor updateDoctor(String id, Doctor updatedDoctor) {

        Doctor existingDoctor = getDoctorById(id);

        existingDoctor.setClinicId(updatedDoctor.getClinicId());
        existingDoctor.setName(updatedDoctor.getName());
        existingDoctor.setPhone(updatedDoctor.getPhone());
        existingDoctor.setEmail(updatedDoctor.getEmail());
        existingDoctor.setSpecialization(
                updatedDoctor.getSpecialization()
        );

        return doctorRepository.save(existingDoctor);
    }
}
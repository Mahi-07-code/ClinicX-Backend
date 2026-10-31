
package com.example.demo.service;

import com.example.demo.Clinic;
import com.example.demo.repository.ClinicRepository;

import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ClinicService {

    private final ClinicRepository clinicRepository;

    public ClinicService(ClinicRepository clinicRepository) {
        this.clinicRepository = clinicRepository;
    }

    // Register clinic
    public Clinic registerClinic(Clinic clinic) {
        clinic.setStatus("Pending");
        return clinicRepository.save(clinic);
    }

    // Get all clinics
    public List<Clinic> getAllClinics() {
        return clinicRepository.findAll();
    }

    // Get clinic by ID
    public Optional<Clinic> getClinicById(String id) {
        return clinicRepository.findById(id);
    }

    // Update clinic profile
    public Clinic updateClinic(String id, Clinic updatedClinic) {

        Clinic existingClinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Clinic not found"
                ));

        existingClinic.setName(updatedClinic.getName());
        existingClinic.setOwnerName(updatedClinic.getOwnerName());
        existingClinic.setPhone(updatedClinic.getPhone());
        existingClinic.setCity(updatedClinic.getCity());
        existingClinic.setArea(updatedClinic.getArea());

        return clinicRepository.save(existingClinic);
    }

    // Update clinic status
    public Clinic updateClinicStatus(String id, String status) {

        if (status == null || status.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Status is required");
        }

        String updatedStatus = status.trim();

        if (updatedStatus.equalsIgnoreCase("On Hold")) {
            updatedStatus = "Hold";
        }

        if (!updatedStatus.equalsIgnoreCase("Pending")
                && !updatedStatus.equalsIgnoreCase("Approved")
                && !updatedStatus.equalsIgnoreCase("Hold")
                && !updatedStatus.equalsIgnoreCase("Declined")) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST, "Invalid clinic status");
        }

        Clinic clinic = clinicRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Clinic not found"));

        clinic.setStatus(updatedStatus);
        return clinicRepository.save(clinic);
    }

    // Search clinics
    public List<Clinic> searchClinics(String keyword, String status) {

        List<Clinic> clinics = clinicRepository.findAll();

        return clinics.stream()
                .filter(clinic -> {

                    if (keyword == null || keyword.trim().isEmpty()) {
                        return true;
                    }

                    String search = keyword.trim().toLowerCase();

                    return contains(clinic.getName(), search)
                            || contains(clinic.getOwnerName(), search)
                            || contains(clinic.getCity(), search)
                            || contains(clinic.getArea(), search);
                })
                .filter(clinic -> {

                    if (status == null || status.trim().isEmpty()) {
                        return true;
                    }

                    return clinic.getStatus() != null
                            && clinic.getStatus().equalsIgnoreCase(status.trim());
                })
                .collect(Collectors.toList());
    }

    private boolean contains(String value, String search) {
        return value != null
                && value.toLowerCase().contains(search);
    }
}
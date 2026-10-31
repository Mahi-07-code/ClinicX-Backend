
package com.example.demo.service;

import com.example.demo.Staff;
import com.example.demo.Branch;
import com.example.demo.repository.StaffRepository;
import com.example.demo.repository.BranchRepository;

import org.springframework.stereotype.Service;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;
import java.util.LinkedHashMap;
import java.util.Map;

@Service
public class StaffService {

    private final StaffRepository staffRepository;
    private final BranchRepository branchRepository;

    public StaffService(
            StaffRepository staffRepository,
            BranchRepository branchRepository) {

        this.staffRepository = staffRepository;
        this.branchRepository = branchRepository;
    }

    // Validate branch assignment
    private void validateBranchAssignment(
            String clinicId, String branchId) {

        if (branchId == null || branchId.trim().isEmpty()) {
            return;
        }

        if (clinicId == null || clinicId.trim().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Clinic ID is required"
            );
        }

        Branch branch = branchRepository.findById(branchId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Branch not found"
                ));

        if (!clinicId.equals(branch.getClinicId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Branch does not belong to this clinic"
            );
        }
    }

    // Add staff
    public Staff addStaff(Staff staff) {

        validateBranchAssignment(
                staff.getClinicId(),
                staff.getBranchId()
        );

        staff.setStatus("Active");

        return staffRepository.save(staff);
    }

    // Get all staff
    public List<Staff> getAllStaff() {
        return staffRepository.findAll();
    }

    // Get staff by clinic
    public List<Staff> getStaffByClinic(String clinicId) {
        return staffRepository.findByClinicId(clinicId);
    }

    // Get staff by ID
    public Staff getStaffById(String id) {
        return staffRepository.findById(id).orElse(null);
    }

    // Update staff
    public Staff updateStaff(String id, Staff updatedStaff) {

        Optional<Staff> optionalStaff =
                staffRepository.findById(id);

        if (optionalStaff.isPresent()) {

            Staff existingStaff = optionalStaff.get();

            validateBranchAssignment(
                    updatedStaff.getClinicId(),
                    updatedStaff.getBranchId()
            );

            existingStaff.setClinicId(updatedStaff.getClinicId());
            existingStaff.setBranchId(updatedStaff.getBranchId());
            existingStaff.setName(updatedStaff.getName());
            existingStaff.setPhone(updatedStaff.getPhone());
            existingStaff.setEmail(updatedStaff.getEmail());
            existingStaff.setRole(updatedStaff.getRole());
            existingStaff.setSpecialization(updatedStaff.getSpecialization());
            existingStaff.setQualification(updatedStaff.getQualification());
            existingStaff.setCertificate(updatedStaff.getCertificate());

            return staffRepository.save(existingStaff);
        }

        return null;
    }

    // Search staff by name or designation
    public List<Staff> searchStaff(String clinicId, String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return staffRepository.findByClinicId(clinicId);
        }

        String searchKeyword = keyword.trim();

        List<Staff> nameResults =
                staffRepository.findByClinicIdAndNameContainingIgnoreCase(
                        clinicId, searchKeyword);

        List<Staff> roleResults =
                staffRepository.findByClinicIdAndRoleContainingIgnoreCase(
                        clinicId, searchKeyword);

        Map<String, Staff> results = new LinkedHashMap<>();

        for (Staff staff : nameResults) {
            results.put(staff.getId(), staff);
        }

        for (Staff staff : roleResults) {
            results.put(staff.getId(), staff);
        }

        return List.copyOf(results.values());
    }

    // Update staff status
    public Staff updateStaffStatus(String id, String status) {

        if (status == null ||
                (!status.equalsIgnoreCase("Active")
                && !status.equalsIgnoreCase("Inactive"))) {

            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Status must be Active or Inactive"
            );
        }

        Optional<Staff> optionalStaff =
                staffRepository.findById(id);

        if (optionalStaff.isPresent()) {

            Staff staff = optionalStaff.get();

            staff.setStatus(
                    status.equalsIgnoreCase("Active")
                            ? "Active"
                            : "Inactive"
            );

            return staffRepository.save(staff);
        }

        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND,
                "Staff not found"
        );
    }
}
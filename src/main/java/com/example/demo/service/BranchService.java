
package com.example.demo.service;

import com.example.demo.Branch;
import com.example.demo.repository.BranchRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    private final BranchRepository branchRepository;

    public BranchService(BranchRepository branchRepository) {
        this.branchRepository = branchRepository;
    }

    // Add a new branch
    public Branch addBranch(Branch branch) {
        branch.setStatus("Active");
        return branchRepository.save(branch);
    }

    // Get all branches
    public List<Branch> getAllBranches() {
        return branchRepository.findAll();
    }

    // Get branches belonging to a particular clinic
    public List<Branch> getBranchesByClinic(String clinicId) {
        return branchRepository.findByClinicId(clinicId);
    }

    // Get one branch using its ID
    public Branch getBranchById(String id) {
        return branchRepository.findById(id).orElse(null);
    }

    // Search branches by name within a clinic
    public List<Branch> searchBranches(String clinicId, String keyword) {
        return branchRepository
                .findByClinicIdAndNameContainingIgnoreCase(clinicId, keyword);
    }

    // Edit branch information and timings
    public Branch updateBranch(String id, Branch updatedBranch) {

        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {

            Branch existingBranch = optionalBranch.get();

            existingBranch.setName(updatedBranch.getName());
            existingBranch.setAddress(updatedBranch.getAddress());
            existingBranch.setCity(updatedBranch.getCity());
            existingBranch.setPhone(updatedBranch.getPhone());
            existingBranch.setOpeningTime(updatedBranch.getOpeningTime());
            existingBranch.setClosingTime(updatedBranch.getClosingTime());

            // Keep the original clinic ID and status unchanged
            return branchRepository.save(existingBranch);
        }

        return null;
    }

    // Update branch status
    public Branch updateBranchStatus(String id, String status) {

        Optional<Branch> optionalBranch = branchRepository.findById(id);

        if (optionalBranch.isPresent()) {

            Branch branch = optionalBranch.get();
            branch.setStatus(status);

            return branchRepository.save(branch);
        }

        return null;
    }
}
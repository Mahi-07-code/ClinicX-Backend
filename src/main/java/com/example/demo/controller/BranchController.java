
package com.example.demo.controller;

import com.example.demo.Branch;
import com.example.demo.service.BranchService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/branches")
@CrossOrigin(origins = "*")
public class BranchController {

    private final BranchService branchService;

    public BranchController(BranchService branchService) {
        this.branchService = branchService;
    }

    // Add a new branch
    @PostMapping
    public Branch addBranch(@RequestBody Branch branch) {
        return branchService.addBranch(branch);
    }

    // Get all branches
    @GetMapping
    public List<Branch> getAllBranches() {
        return branchService.getAllBranches();
    }

    // Get branches by clinic ID
    @GetMapping("/clinic/{clinicId}")
    public List<Branch> getBranchesByClinic(
            @PathVariable String clinicId) {
        return branchService.getBranchesByClinic(clinicId);
    }

    // Search branches by name within a clinic
    @GetMapping("/clinic/{clinicId}/search")
    public List<Branch> searchBranches(
            @PathVariable String clinicId,
            @RequestParam String keyword) {
        return branchService.searchBranches(clinicId, keyword);
    }

    // Get one branch by ID
    @GetMapping("/{id}")
    public Branch getBranchById(@PathVariable String id) {
        return branchService.getBranchById(id);
    }

    // Edit branch details and timings
    @PutMapping("/{id}")
    public Branch updateBranch(
            @PathVariable String id,
            @RequestBody Branch branch) {
        return branchService.updateBranch(id, branch);
    }

    // Update branch status
    @PatchMapping("/{id}/status")
    public Branch updateBranchStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");

        return branchService.updateBranchStatus(id, status);
    }
}
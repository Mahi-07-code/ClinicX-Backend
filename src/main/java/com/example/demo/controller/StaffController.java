
package com.example.demo.controller;

import com.example.demo.Staff;
import com.example.demo.service.StaffService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/staff")
@CrossOrigin(origins = "*")
public class StaffController {

    private final StaffService staffService;

    public StaffController(StaffService staffService) {
        this.staffService = staffService;
    }

    @PostMapping
    public Staff addStaff(@RequestBody Staff staff) {
        return staffService.addStaff(staff);
    }

    @GetMapping
    public List<Staff> getAllStaff() {
        return staffService.getAllStaff();
    }

    @GetMapping("/clinic/{clinicId}")
    public List<Staff> getStaffByClinic(
            @PathVariable String clinicId) {
        return staffService.getStaffByClinic(clinicId);
    }

    @GetMapping("/clinic/{clinicId}/search")
    public List<Staff> searchStaff(
            @PathVariable String clinicId,
            @RequestParam(required = false) String keyword) {

        return staffService.searchStaff(clinicId, keyword);
    }

    @GetMapping("/{id}")
    public Staff getStaffById(@PathVariable String id) {
        return staffService.getStaffById(id);
    }

    @PutMapping("/{id}")
    public Staff updateStaff(
            @PathVariable String id,
            @RequestBody Staff staff) {

        return staffService.updateStaff(id, staff);
    }

    @PatchMapping("/{id}/status")
    public Staff updateStaffStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");

        return staffService.updateStaffStatus(id, status);
    }
}
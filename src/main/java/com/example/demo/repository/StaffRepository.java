package com.example.demo.repository;

import com.example.demo.Staff;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface StaffRepository extends MongoRepository<Staff, String> {

    List<Staff> findByClinicId(String clinicId);

    List<Staff> findByClinicIdAndNameContainingIgnoreCase(
            String clinicId, String name);

    List<Staff> findByClinicIdAndRoleContainingIgnoreCase(
            String clinicId, String role);

    Optional<Staff> findByPhoneAndRole(String phone, String role);
}
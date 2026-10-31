
package com.example.demo.repository;

import com.example.demo.Branch;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface BranchRepository extends MongoRepository<Branch, String> {

    List<Branch> findByClinicId(String clinicId);

    List<Branch> findByClinicIdAndNameContainingIgnoreCase(
            String clinicId, String name);
}

package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "doctors")
public class Doctor {

    @Id
    private String id;

    private String clinicId;
    private String name;
    private String phone;
    private String email;
    private String specialization;
    private String status = "Active";

    public Doctor() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getClinicId() { return clinicId; }
    public void setClinicId(String clinicId) { this.clinicId = clinicId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getPhone() { return phone; }
    public void setPhone(String phone) { this.phone = phone; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}


package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "subscriptions")
public class Subscription {

    @Id
    private String id;

    private String clinicId;
    private String planName;
    private String startDate;
    private String endDate;
    private int doctorLimit;
    private int additionalDoctors;
    private double totalAmount;
    private String status = "Active";

    public Subscription() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClinicId() {
        return clinicId;
    }

    public void setClinicId(String clinicId) {
        this.clinicId = clinicId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public int getDoctorLimit() {
        return doctorLimit;
    }

    public void setDoctorLimit(int doctorLimit) {
        this.doctorLimit = doctorLimit;
    }

    public int getAdditionalDoctors() {
        return additionalDoctors;
    }

    public void setAdditionalDoctors(int additionalDoctors) {
        this.additionalDoctors = additionalDoctors;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package com.example.demo;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "consultations")
public class Consultation {

    @Id
    private String id;

    private String clinicId;
    private String appointmentId;
    private String patientId;
    private String doctorId;
    private String patientName;
    private String doctorName;
    private String notes;
    private String prescription;
    private String consultationDate;
    private String followUpDate;

    public Consultation() {
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getClinicId() { return clinicId; }
    public void setClinicId(String clinicId) { this.clinicId = clinicId; }

    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getPatientName() { return patientName; }
    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDoctorName() { return doctorName; }
    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }

    public String getPrescription() { return prescription; }
    public void setPrescription(String prescription) {
        this.prescription = prescription;
    }

    public String getConsultationDate() { return consultationDate; }
    public void setConsultationDate(String consultationDate) {
        this.consultationDate = consultationDate;
    }

    public String getFollowUpDate() { return followUpDate; }
    public void setFollowUpDate(String followUpDate) {
        this.followUpDate = followUpDate;
    }
}

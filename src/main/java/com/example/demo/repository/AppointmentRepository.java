
package com.example.demo.repository;

import com.example.demo.Appointment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface AppointmentRepository
        extends MongoRepository<Appointment, String> {

    // Find appointments by clinic
    List<Appointment> findByClinicId(String clinicId);

    // Find appointments by clinic and date
    List<Appointment> findByClinicIdAndAppointmentDate(
            String clinicId, String appointmentDate);

    // Find appointments by doctor
    List<Appointment> findByDoctorId(String doctorId);

    // Find appointments by doctor and date
    List<Appointment> findByDoctorIdAndAppointmentDate(
            String doctorId, String appointmentDate);

    // Search appointments by patient name
    List<Appointment> findByClinicIdAndPatientNameContainingIgnoreCase(
            String clinicId, String patientName);

    // Search appointments by token number
    List<Appointment> findByClinicIdAndTokenNumber(
            String clinicId, int tokenNumber);
}
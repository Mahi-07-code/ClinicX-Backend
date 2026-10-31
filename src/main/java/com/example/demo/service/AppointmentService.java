
package com.example.demo.service;

import com.example.demo.Appointment;
import com.example.demo.repository.AppointmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppointmentService {

    private final AppointmentRepository appointmentRepository;

    public AppointmentService(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    // Book a new appointment and generate token
    public Appointment bookAppointment(Appointment appointment) {

        List<Appointment> appointments =
                appointmentRepository.findByClinicIdAndAppointmentDate(
                        appointment.getClinicId(),
                        appointment.getAppointmentDate()
                );

        int nextToken = appointments.stream()
                .mapToInt(Appointment::getTokenNumber)
                .max()
                .orElse(0) + 1;

        appointment.setTokenNumber(nextToken);
        appointment.setStatus("Waiting");

        return appointmentRepository.save(appointment);
    }

    // Get all appointments
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    // Get appointments by clinic
    public List<Appointment> getAppointmentsByClinic(String clinicId) {
        return appointmentRepository.findByClinicId(clinicId);
    }

    // Get appointments by clinic and date
    public List<Appointment> getAppointmentsByDate(
            String clinicId, String date) {

        return appointmentRepository.findByClinicIdAndAppointmentDate(
                clinicId, date);
    }

    // Search appointments by patient name or token number
    public List<Appointment> searchAppointments(
            String clinicId, String keyword) {

        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }

        String searchKeyword = keyword.trim();

        // Search by patient name
        List<Appointment> nameResults =
                appointmentRepository
                        .findByClinicIdAndPatientNameContainingIgnoreCase(
                                clinicId, searchKeyword);

        // Search by token number if keyword is numeric
        if (searchKeyword.matches("\\d+")) {

            int tokenNumber = Integer.parseInt(searchKeyword);

            List<Appointment> tokenResults =
                    appointmentRepository.findByClinicIdAndTokenNumber(
                            clinicId, tokenNumber);

            // Combine both results without duplicates
            for (Appointment appointment : tokenResults) {
                if (nameResults.stream().noneMatch(
                        existing -> existing.getId().equals(
                                appointment.getId()))) {
                    nameResults.add(appointment);
                }
            }
        }

        return nameResults;
    }

    // Update appointment status
    public Appointment updateAppointmentStatus(String id, String status) {

        Optional<Appointment> optionalAppointment =
                appointmentRepository.findById(id);

        if (optionalAppointment.isPresent()) {
            Appointment appointment = optionalAppointment.get();
            appointment.setStatus(status);
            return appointmentRepository.save(appointment);
        }

        return null;
    }

    // Get appointments by doctor
    public List<Appointment> getAppointmentsByDoctor(String doctorId) {
        return appointmentRepository.findByDoctorId(doctorId);
    }

    // Get appointments by doctor and date
    public List<Appointment> getAppointmentsByDoctorAndDate(
            String doctorId, String date) {

        return appointmentRepository.findByDoctorIdAndAppointmentDate(
                doctorId, date);
    }
}
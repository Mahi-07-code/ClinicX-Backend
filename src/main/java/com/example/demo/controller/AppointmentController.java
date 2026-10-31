
package com.example.demo.controller;

import com.example.demo.Appointment;
import com.example.demo.service.AppointmentService;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/appointments")
@CrossOrigin(origins = "*")
public class AppointmentController {

    private final AppointmentService appointmentService;

    public AppointmentController(AppointmentService appointmentService) {
        this.appointmentService = appointmentService;
    }

    // Book appointment
    @PostMapping
    public Appointment bookAppointment(
            @RequestBody Appointment appointment) {

        return appointmentService.bookAppointment(appointment);
    }

    // Get all appointments
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    // Get appointments by clinic
    @GetMapping("/clinic/{clinicId}")
    public List<Appointment> getAppointmentsByClinic(
            @PathVariable String clinicId) {

        return appointmentService.getAppointmentsByClinic(clinicId);
    }

    // Search appointments by patient name or token number
    @GetMapping("/clinic/{clinicId}/search")
    public List<Appointment> searchAppointments(
            @PathVariable String clinicId,
            @RequestParam String keyword) {

        return appointmentService.searchAppointments(clinicId, keyword);
    }

    // Get appointments by clinic and date
    @GetMapping("/clinic/{clinicId}/date/{date}")
    public List<Appointment> getAppointmentsByDate(
            @PathVariable String clinicId,
            @PathVariable String date) {

        return appointmentService.getAppointmentsByDate(clinicId, date);
    }

    // Update appointment status
    @PatchMapping("/{id}/status")
    public Appointment updateAppointmentStatus(
            @PathVariable String id,
            @RequestBody Map<String, String> request) {

        String status = request.get("status");

        return appointmentService.updateAppointmentStatus(id, status);
    }

    // Get appointments by doctor
    @GetMapping("/doctor/{doctorId}")
    public List<Appointment> getAppointmentsByDoctor(
            @PathVariable String doctorId) {

        return appointmentService.getAppointmentsByDoctor(doctorId);
    }

    // Get appointments by doctor and date
    @GetMapping("/doctor/{doctorId}/date/{date}")
    public List<Appointment> getAppointmentsByDoctorAndDate(
            @PathVariable String doctorId,
            @PathVariable String date) {

        return appointmentService.getAppointmentsByDoctorAndDate(
                doctorId, date);
    }
}

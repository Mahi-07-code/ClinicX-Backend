
package com.example.demo.service;

import com.example.demo.repository.ClinicRepository;
import com.example.demo.repository.DoctorRepository;
import com.example.demo.repository.StaffRepository;

import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class OtpService {

    private final ClinicRepository clinicRepository;
    private final StaffRepository staffRepository;
    private final DoctorRepository doctorRepository;

    private final SecureRandom random = new SecureRandom();

    private final Map<String, OtpData> otpStore = new ConcurrentHashMap<>();

    public OtpService(
            ClinicRepository clinicRepository,
            StaffRepository staffRepository,
            DoctorRepository doctorRepository) {
        this.clinicRepository = clinicRepository;
        this.staffRepository = staffRepository;
        this.doctorRepository = doctorRepository;
    }

    public String generateOtp(String phone, String role) {

        if (!isRegisteredUser(phone, role)) {
            throw new RuntimeException("No active account found for this mobile number and role.");
        }

        String otp = String.format("%06d", random.nextInt(1000000));

        String key = phone + ":" + role;

        otpStore.put(key, new OtpData(
                otp,
                LocalDateTime.now().plusMinutes(5)
        ));

        return otp;
    }

    public boolean verifyOtp(String phone, String role, String otp) {

        String key = phone + ":" + role;

        OtpData storedData = otpStore.get(key);

        if (storedData == null) {
            return false;
        }

        if (LocalDateTime.now().isAfter(storedData.expiryTime)) {
            otpStore.remove(key);
            return false;
        }

        if (!storedData.otp.equals(otp)) {
            return false;
        }

        otpStore.remove(key);
        return true;
    }

    private boolean isRegisteredUser(String phone, String role) {

        if (phone == null || phone.isBlank() || role == null) {
            return false;
        }

        switch (role.trim().toLowerCase()) {

            case "clinic admin":
                return clinicRepository.findByPhone(phone)
                        .map(clinic -> "Approved".equalsIgnoreCase(clinic.getStatus()))
                        .orElse(false);

            case "receptionist":
                return staffRepository.findByPhoneAndRole(phone, "Receptionist")
                        .map(staff -> "Active".equalsIgnoreCase(staff.getStatus()))
                        .orElse(false);

            case "doctor":
                return doctorRepository.findByPhone(phone)
                        .map(doctor -> "Active".equalsIgnoreCase(doctor.getStatus()))
                        .orElse(false);

            default:
                return false;
        }
    }

    private static class OtpData {

        private final String otp;
        private final LocalDateTime expiryTime;

        private OtpData(String otp, LocalDateTime expiryTime) {
            this.otp = otp;
            this.expiryTime = expiryTime;
        }
    }
}
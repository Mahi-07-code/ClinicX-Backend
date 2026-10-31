
package com.example.demo.controller;

import com.example.demo.OtpRequest;
import com.example.demo.service.OtpService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class OtpController {

    private final OtpService otpService;

    public OtpController(OtpService otpService) {
        this.otpService = otpService;
    }

    @PostMapping("/send-otp")
    public ResponseEntity<?> sendOtp(@RequestBody OtpRequest request) {

        try {
            String otp = otpService.generateOtp(
                    request.getPhone(),
                    request.getRole()
            );

            return ResponseEntity.ok(Map.of(
                    "message", "Demo OTP generated successfully.",
                    "demoOtp", otp,
                    "note", "Demo only. No SMS was sent."
            ));

        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(
                    Map.of("message", e.getMessage())
            );
        }
    }

    @PostMapping("/verify-otp")
    public ResponseEntity<?> verifyOtp(@RequestBody OtpRequest request) {

        boolean verified = otpService.verifyOtp(
                request.getPhone(),
                request.getRole(),
                request.getOtp()
        );

        if (verified) {
            return ResponseEntity.ok(Map.of(
                    "message", "OTP verified successfully.",
                    "loginStatus", "Success",
                    "note", "Demo verification only. No secure session or token was created."
            ));
        }

        return ResponseEntity.badRequest().body(
                Map.of("message", "Invalid or expired OTP.")
        );
    }
}
package com.farmassist.controller;

import com.farmassist.dto.AuthResponse;
import com.farmassist.dto.LoginRequest;
import com.farmassist.dto.RegisterRequest;
import com.farmassist.entity.User;
import com.farmassist.service.FarmerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class AuthController {

    private final FarmerService farmerService;

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {
        AuthResponse response = farmerService.login(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {
        AuthResponse response = farmerService.register(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @GetMapping("/farmers")
    public ResponseEntity<List<User>> getAllFarmers() {
        return ResponseEntity.ok(farmerService.getAllFarmers());
    }
}

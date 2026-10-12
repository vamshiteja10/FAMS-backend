package com.farmassist.controller;

import com.farmassist.entity.FarmerProfile;
import com.farmassist.entity.User;
import com.farmassist.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/farmers")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class FarmerController {

    private final FarmerService farmerService;

    @GetMapping
    public ResponseEntity<List<User>> getAllFarmers() {
        return ResponseEntity.ok(farmerService.getAllFarmers());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getFarmerById(@PathVariable Long id) {
        return ResponseEntity.ok(farmerService.getUserById(id));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<FarmerProfile> getFarmerProfile(@PathVariable Long id) {
        return ResponseEntity.ok(farmerService.getFarmerProfile(id));
    }

    @PostMapping
    public ResponseEntity<User> createFarmer(@RequestBody com.farmassist.dto.RegisterRequest request) {
        return new ResponseEntity<>(farmerService.createFarmer(request), org.springframework.http.HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> updateFarmer(@PathVariable Long id, @RequestBody User user) {
        return ResponseEntity.ok(farmerService.updateFarmer(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFarmer(@PathVariable Long id) {
        farmerService.deleteFarmer(id);
        return ResponseEntity.noContent().build();
    }
}

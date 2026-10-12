package com.farmassist.service;

import com.farmassist.dto.AuthResponse;
import com.farmassist.dto.LoginRequest;
import com.farmassist.dto.RegisterRequest;
import com.farmassist.entity.FarmerProfile;
import com.farmassist.entity.User;

import java.util.List;

public interface FarmerService {
    AuthResponse login(LoginRequest request);
    AuthResponse register(RegisterRequest request);
    List<User> getAllFarmers();
    User getUserById(Long id);
    User createFarmer(RegisterRequest request);
    User updateFarmer(Long id, User updateData);
    void deleteFarmer(Long id);
    FarmerProfile getFarmerProfile(Long userId);
    FarmerProfile updateFarmerProfile(Long userId, FarmerProfile profile);
}

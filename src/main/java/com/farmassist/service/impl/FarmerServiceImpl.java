package com.farmassist.service.impl;

import com.farmassist.dto.AuthResponse;
import com.farmassist.dto.LoginRequest;
import com.farmassist.dto.RegisterRequest;
import com.farmassist.entity.FarmerProfile;
import com.farmassist.entity.User;
import com.farmassist.exception.BadRequestException;
import com.farmassist.exception.ResourceNotFoundException;
import com.farmassist.repository.FarmerProfileRepository;
import com.farmassist.repository.UserRepository;
import com.farmassist.service.FarmerService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FarmerServiceImpl implements FarmerService {

    private final UserRepository userRepository;
    private final FarmerProfileRepository profileRepository;

    @Override
    public AuthResponse login(LoginRequest request) {
        User user = userRepository.findByUsername(request.getUsername())
                .orElseGet(() -> userRepository.findByEmail(request.getUsername())
                        .orElseThrow(() -> new ResourceNotFoundException("Invalid username or password")));

        if (!user.getPassword().equals(request.getPassword())) {
            throw new BadRequestException("Invalid username or password");
        }

        return AuthResponse.builder()
                .id(user.getId())
                .username(user.getUsername())
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .token("mock-jwt-token-" + user.getId())
                .message("Login successful")
                .build();
    }

    @Override
    @Transactional
    public AuthResponse register(RegisterRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new BadRequestException("Username already exists");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new BadRequestException("Email already exists");
        }

        String role = (request.getRole() != null && !request.getRole().isBlank()) 
                ? request.getRole() : "ROLE_FARMER";

        User user = User.builder()
                .username(request.getUsername())
                .password(request.getPassword()) // Plain/encoded as requested for moderate academic setup
                .fullName(request.getFullName())
                .email(request.getEmail())
                .phone(request.getPhone())
                .role(role)
                .build();

        User savedUser = userRepository.save(user);

        // If farmer, also create default farmer profile
        if ("ROLE_FARMER".equals(role)) {
            FarmerProfile profile = FarmerProfile.builder()
                    .user(savedUser)
                    .village(request.getVillage())
                    .district(request.getDistrict())
                    .state(request.getState())
                    .totalExperienceYears(request.getTotalExperienceYears() != null ? request.getTotalExperienceYears() : 2)
                    .primaryWaterSource(request.getPrimaryWaterSource())
                    .build();
            profileRepository.save(profile);
        }

        return AuthResponse.builder()
                .id(savedUser.getId())
                .username(savedUser.getUsername())
                .fullName(savedUser.getFullName())
                .email(savedUser.getEmail())
                .role(savedUser.getRole())
                .token("mock-jwt-token-" + savedUser.getId())
                .message("User registered successfully")
                .build();
    }

    @Override
    public List<User> getAllFarmers() {
        return userRepository.findByRole("ROLE_FARMER");
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + id));
    }

    @Override
    public FarmerProfile getFarmerProfile(Long userId) {
        return profileRepository.findByUserId(userId)
                .orElseGet(() -> {
                    User user = getUserById(userId);
                    FarmerProfile defaultProfile = FarmerProfile.builder()
                            .user(user)
                            .village("Not Specified")
                            .district("Not Specified")
                            .state("Not Specified")
                            .totalExperienceYears(1)
                            .primaryWaterSource("Canal / Well")
                            .build();
                    return profileRepository.save(defaultProfile);
                });
    }

    @Override
    @Transactional
    public User createFarmer(RegisterRequest request) {
        AuthResponse auth = register(request);
        return getUserById(auth.getId());
    }

    @Override
    @Transactional
    public User updateFarmer(Long id, User updateData) {
        User existing = getUserById(id);
        if (updateData.getFullName() != null) existing.setFullName(updateData.getFullName());
        if (updateData.getEmail() != null) existing.setEmail(updateData.getEmail());
        if (updateData.getPhone() != null) existing.setPhone(updateData.getPhone());
        return userRepository.save(existing);
    }

    @Override
    @Transactional
    public void deleteFarmer(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public FarmerProfile updateFarmerProfile(Long userId, FarmerProfile updateData) {
        FarmerProfile profile = getFarmerProfile(userId);
        if (updateData.getVillage() != null) profile.setVillage(updateData.getVillage());
        if (updateData.getDistrict() != null) profile.setDistrict(updateData.getDistrict());
        if (updateData.getState() != null) profile.setState(updateData.getState());
        if (updateData.getTotalExperienceYears() != null) profile.setTotalExperienceYears(updateData.getTotalExperienceYears());
        if (updateData.getPrimaryWaterSource() != null) profile.setPrimaryWaterSource(updateData.getPrimaryWaterSource());
        return profileRepository.save(profile);
    }
}

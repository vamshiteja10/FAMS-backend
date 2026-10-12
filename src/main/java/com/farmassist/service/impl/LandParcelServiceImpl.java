package com.farmassist.service.impl;

import com.farmassist.dto.LandParcelDto;
import com.farmassist.entity.LandParcel;
import com.farmassist.entity.User;
import com.farmassist.exception.ResourceNotFoundException;
import com.farmassist.repository.LandParcelRepository;
import com.farmassist.repository.UserRepository;
import com.farmassist.service.LandParcelService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LandParcelServiceImpl implements LandParcelService {

    private final LandParcelRepository landParcelRepository;
    private final UserRepository userRepository;

    @Override
    public List<LandParcelDto> getAllLandParcels() {
        return landParcelRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<LandParcelDto> getLandParcelsByUserId(Long userId) {
        return landParcelRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public LandParcelDto getLandParcelById(Long id) {
        LandParcel parcel = landParcelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Land Parcel not found with id: " + id));
        return mapToDto(parcel);
    }

    @Override
    @Transactional
    public LandParcelDto createLandParcel(LandParcelDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + dto.getUserId()));

        LandParcel parcel = LandParcel.builder()
                .user(user)
                .parcelName(dto.getParcelName())
                .surveyNumber(dto.getSurveyNumber())
                .totalAreaAcres(dto.getTotalAreaAcres())
                .soilType(dto.getSoilType())
                .irrigationType(dto.getIrrigationType())
                .villageLocation(dto.getVillageLocation())
                .build();

        return mapToDto(landParcelRepository.save(parcel));
    }

    @Override
    @Transactional
    public LandParcelDto updateLandParcel(Long id, LandParcelDto dto) {
        LandParcel parcel = landParcelRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Land Parcel not found with id: " + id));

        parcel.setParcelName(dto.getParcelName());
        parcel.setSurveyNumber(dto.getSurveyNumber());
        parcel.setTotalAreaAcres(dto.getTotalAreaAcres());
        parcel.setSoilType(dto.getSoilType());
        parcel.setIrrigationType(dto.getIrrigationType());
        parcel.setVillageLocation(dto.getVillageLocation());

        return mapToDto(landParcelRepository.save(parcel));
    }

    @Override
    @Transactional
    public void deleteLandParcel(Long id) {
        if (!landParcelRepository.existsById(id)) {
            throw new ResourceNotFoundException("Land Parcel not found with id: " + id);
        }
        landParcelRepository.deleteById(id);
    }

    private LandParcelDto mapToDto(LandParcel parcel) {
        return LandParcelDto.builder()
                .id(parcel.getId())
                .userId(parcel.getUser().getId())
                .farmerName(parcel.getUser().getFullName())
                .parcelName(parcel.getParcelName())
                .surveyNumber(parcel.getSurveyNumber())
                .totalAreaAcres(parcel.getTotalAreaAcres())
                .soilType(parcel.getSoilType())
                .irrigationType(parcel.getIrrigationType())
                .villageLocation(parcel.getVillageLocation())
                .createdAt(parcel.getCreatedAt())
                .build();
    }
}

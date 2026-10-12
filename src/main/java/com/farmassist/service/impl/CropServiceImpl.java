package com.farmassist.service.impl;

import com.farmassist.dto.CropDto;
import com.farmassist.entity.Crop;
import com.farmassist.entity.LandParcel;
import com.farmassist.entity.User;
import com.farmassist.exception.ResourceNotFoundException;
import com.farmassist.repository.CropRepository;
import com.farmassist.repository.LandParcelRepository;
import com.farmassist.repository.UserRepository;
import com.farmassist.service.CropService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CropServiceImpl implements CropService {

    private final CropRepository cropRepository;
    private final LandParcelRepository landParcelRepository;
    private final UserRepository userRepository;

    @Override
    public List<CropDto> getAllCrops() {
        return cropRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CropDto> getCropsByUserId(Long userId) {
        return cropRepository.findByUserId(userId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<CropDto> getCropsByLandParcelId(Long landParcelId) {
        return cropRepository.findByLandParcelId(landParcelId).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public CropDto getCropById(Long id) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + id));
        return mapToDto(crop);
    }

    @Override
    @Transactional
    public CropDto createCrop(CropDto dto) {
        User user = userRepository.findById(dto.getUserId())
                .orElseThrow(() -> new ResourceNotFoundException("Farmer not found with id: " + dto.getUserId()));

        LandParcel parcel = landParcelRepository.findById(dto.getLandParcelId())
                .orElseThrow(() -> new ResourceNotFoundException("Land parcel not found with id: " + dto.getLandParcelId()));

        Crop crop = Crop.builder()
                .user(user)
                .landParcel(parcel)
                .cropName(dto.getCropName())
                .variety(dto.getVariety())
                .season(dto.getSeason())
                .sowingDate(dto.getSowingDate())
                .expectedHarvestDate(dto.getExpectedHarvestDate())
                .expectedYieldQuintals(dto.getExpectedYieldQuintals())
                .status(dto.getStatus() != null ? dto.getStatus() : "GROWING")
                .notes(dto.getNotes())
                .build();

        return mapToDto(cropRepository.save(crop));
    }

    @Override
    @Transactional
    public CropDto updateCrop(Long id, CropDto dto) {
        Crop crop = cropRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Crop not found with id: " + id));

        if (dto.getLandParcelId() != null && !dto.getLandParcelId().equals(crop.getLandParcel().getId())) {
            LandParcel parcel = landParcelRepository.findById(dto.getLandParcelId())
                    .orElseThrow(() -> new ResourceNotFoundException("Land parcel not found with id: " + dto.getLandParcelId()));
            crop.setLandParcel(parcel);
        }

        crop.setCropName(dto.getCropName());
        crop.setVariety(dto.getVariety());
        crop.setSeason(dto.getSeason());
        crop.setSowingDate(dto.getSowingDate());
        crop.setExpectedHarvestDate(dto.getExpectedHarvestDate());
        crop.setExpectedYieldQuintals(dto.getExpectedYieldQuintals());
        if (dto.getStatus() != null) {
            crop.setStatus(dto.getStatus());
        }
        crop.setNotes(dto.getNotes());

        return mapToDto(cropRepository.save(crop));
    }

    @Override
    @Transactional
    public void deleteCrop(Long id) {
        if (!cropRepository.existsById(id)) {
            throw new ResourceNotFoundException("Crop not found with id: " + id);
        }
        cropRepository.deleteById(id);
    }

    private CropDto mapToDto(Crop crop) {
        return CropDto.builder()
                .id(crop.getId())
                .landParcelId(crop.getLandParcel().getId())
                .landParcelName(crop.getLandParcel().getParcelName())
                .userId(crop.getUser().getId())
                .farmerName(crop.getUser().getFullName())
                .cropName(crop.getCropName())
                .variety(crop.getVariety())
                .season(crop.getSeason())
                .sowingDate(crop.getSowingDate())
                .expectedHarvestDate(crop.getExpectedHarvestDate())
                .expectedYieldQuintals(crop.getExpectedYieldQuintals())
                .status(crop.getStatus())
                .notes(crop.getNotes())
                .createdAt(crop.getCreatedAt())
                .build();
    }
}

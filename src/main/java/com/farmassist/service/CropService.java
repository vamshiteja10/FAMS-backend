package com.farmassist.service;

import com.farmassist.dto.CropDto;

import java.util.List;

public interface CropService {
    List<CropDto> getAllCrops();
    List<CropDto> getCropsByUserId(Long userId);
    List<CropDto> getCropsByLandParcelId(Long landParcelId);
    CropDto getCropById(Long id);
    CropDto createCrop(CropDto dto);
    CropDto updateCrop(Long id, CropDto dto);
    void deleteCrop(Long id);
}

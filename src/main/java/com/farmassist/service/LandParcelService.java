package com.farmassist.service;

import com.farmassist.dto.LandParcelDto;
import com.farmassist.entity.LandParcel;

import java.util.List;

public interface LandParcelService {
    List<LandParcelDto> getAllLandParcels();
    List<LandParcelDto> getLandParcelsByUserId(Long userId);
    LandParcelDto getLandParcelById(Long id);
    LandParcelDto createLandParcel(LandParcelDto dto);
    LandParcelDto updateLandParcel(Long id, LandParcelDto dto);
    void deleteLandParcel(Long id);
}

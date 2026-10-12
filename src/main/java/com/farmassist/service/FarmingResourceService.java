package com.farmassist.service;

import com.farmassist.dto.FarmingResourceDto;

import java.util.List;

public interface FarmingResourceService {
    List<FarmingResourceDto> getAllResources();
    List<FarmingResourceDto> getResourcesByCategory(String category);
    FarmingResourceDto getResourceById(Long id);
    FarmingResourceDto createResource(FarmingResourceDto dto);
    FarmingResourceDto updateResource(Long id, FarmingResourceDto dto);
    void deleteResource(Long id);
}

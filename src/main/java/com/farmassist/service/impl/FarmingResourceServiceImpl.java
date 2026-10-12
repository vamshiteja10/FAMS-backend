package com.farmassist.service.impl;

import com.farmassist.dto.FarmingResourceDto;
import com.farmassist.entity.FarmingResource;
import com.farmassist.exception.ResourceNotFoundException;
import com.farmassist.repository.FarmingResourceRepository;
import com.farmassist.service.FarmingResourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FarmingResourceServiceImpl implements FarmingResourceService {

    private final FarmingResourceRepository resourceRepository;

    @Override
    public List<FarmingResourceDto> getAllResources() {
        return resourceRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<FarmingResourceDto> getResourcesByCategory(String category) {
        return resourceRepository.findByCategory(category).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public FarmingResourceDto getResourceById(Long id) {
        FarmingResource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farming resource not found with id: " + id));
        return mapToDto(resource);
    }

    @Override
    @Transactional
    public FarmingResourceDto createResource(FarmingResourceDto dto) {
        FarmingResource resource = FarmingResource.builder()
                .title(dto.getTitle())
                .category(dto.getCategory())
                .description(dto.getDescription())
                .recommendedDosage(dto.getRecommendedDosage())
                .contactOrLink(dto.getContactOrLink())
                .status(dto.getStatus() != null ? dto.getStatus() : "AVAILABLE")
                .build();

        return mapToDto(resourceRepository.save(resource));
    }

    @Override
    @Transactional
    public FarmingResourceDto updateResource(Long id, FarmingResourceDto dto) {
        FarmingResource resource = resourceRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Farming resource not found with id: " + id));

        resource.setTitle(dto.getTitle());
        resource.setCategory(dto.getCategory());
        resource.setDescription(dto.getDescription());
        resource.setRecommendedDosage(dto.getRecommendedDosage());
        resource.setContactOrLink(dto.getContactOrLink());
        if (dto.getStatus() != null) {
            resource.setStatus(dto.getStatus());
        }

        return mapToDto(resourceRepository.save(resource));
    }

    @Override
    @Transactional
    public void deleteResource(Long id) {
        if (!resourceRepository.existsById(id)) {
            throw new ResourceNotFoundException("Farming resource not found with id: " + id);
        }
        resourceRepository.deleteById(id);
    }

    private FarmingResourceDto mapToDto(FarmingResource res) {
        return FarmingResourceDto.builder()
                .id(res.getId())
                .title(res.getTitle())
                .category(res.getCategory())
                .description(res.getDescription())
                .recommendedDosage(res.getRecommendedDosage())
                .contactOrLink(res.getContactOrLink())
                .status(res.getStatus())
                .createdAt(res.getCreatedAt())
                .build();
    }
}

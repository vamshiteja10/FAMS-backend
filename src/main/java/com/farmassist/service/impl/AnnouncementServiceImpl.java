package com.farmassist.service.impl;

import com.farmassist.dto.AnnouncementDto;
import com.farmassist.entity.Announcement;
import com.farmassist.exception.ResourceNotFoundException;
import com.farmassist.repository.AnnouncementRepository;
import com.farmassist.service.AnnouncementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AnnouncementServiceImpl implements AnnouncementService {

    private final AnnouncementRepository announcementRepository;

    @Override
    public List<AnnouncementDto> getAllAnnouncements() {
        return announcementRepository.findAllByOrderByCreatedAtDesc().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<AnnouncementDto> getAnnouncementsByCategory(String category) {
        return announcementRepository.findByCategory(category).stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public AnnouncementDto getAnnouncementById(Long id) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found with id: " + id));
        return mapToDto(announcement);
    }

    @Override
    @Transactional
    public AnnouncementDto createAnnouncement(AnnouncementDto dto) {
        Announcement announcement = Announcement.builder()
                .title(dto.getTitle())
                .category(dto.getCategory())
                .content(dto.getContent())
                .urgency(dto.getUrgency() != null ? dto.getUrgency() : "MEDIUM")
                .postedBy(dto.getPostedBy() != null ? dto.getPostedBy() : "Agricultural Extension Officer")
                .build();

        return mapToDto(announcementRepository.save(announcement));
    }

    @Override
    @Transactional
    public AnnouncementDto updateAnnouncement(Long id, AnnouncementDto dto) {
        Announcement announcement = announcementRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Announcement not found with id: " + id));

        announcement.setTitle(dto.getTitle());
        announcement.setCategory(dto.getCategory());
        announcement.setContent(dto.getContent());
        if (dto.getUrgency() != null) {
            announcement.setUrgency(dto.getUrgency());
        }
        if (dto.getPostedBy() != null) {
            announcement.setPostedBy(dto.getPostedBy());
        }

        return mapToDto(announcementRepository.save(announcement));
    }

    @Override
    @Transactional
    public void deleteAnnouncement(Long id) {
        if (!announcementRepository.existsById(id)) {
            throw new ResourceNotFoundException("Announcement not found with id: " + id);
        }
        announcementRepository.deleteById(id);
    }

    private AnnouncementDto mapToDto(Announcement ann) {
        return AnnouncementDto.builder()
                .id(ann.getId())
                .title(ann.getTitle())
                .category(ann.getCategory())
                .content(ann.getContent())
                .urgency(ann.getUrgency())
                .postedBy(ann.getPostedBy())
                .createdAt(ann.getCreatedAt())
                .build();
    }
}

package com.farmassist.service;

import com.farmassist.dto.AnnouncementDto;

import java.util.List;

public interface AnnouncementService {
    List<AnnouncementDto> getAllAnnouncements();
    List<AnnouncementDto> getAnnouncementsByCategory(String category);
    AnnouncementDto getAnnouncementById(Long id);
    AnnouncementDto createAnnouncement(AnnouncementDto dto);
    AnnouncementDto updateAnnouncement(Long id, AnnouncementDto dto);
    void deleteAnnouncement(Long id);
}

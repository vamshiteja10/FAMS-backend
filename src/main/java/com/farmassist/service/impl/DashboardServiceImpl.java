package com.farmassist.service.impl;

import com.farmassist.dto.AnnouncementDto;
import com.farmassist.dto.CropDto;
import com.farmassist.dto.CultivationRecordDto;
import com.farmassist.dto.DashboardStatsDto;
import com.farmassist.entity.LandParcel;
import com.farmassist.repository.*;
import com.farmassist.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DashboardServiceImpl implements DashboardService {

    private final UserRepository userRepository;
    private final LandParcelRepository landParcelRepository;
    private final CropRepository cropRepository;
    private final CultivationRecordRepository recordRepository;
    private final FarmingResourceRepository resourceRepository;
    private final AnnouncementRepository announcementRepository;

    private final CropService cropService;
    private final CultivationRecordService recordService;
    private final AnnouncementService announcementService;

    @Override
    public DashboardStatsDto getOverallStats() {
        long totalFarmers = userRepository.findByRole("ROLE_FARMER").size();
        long totalLandParcels = landParcelRepository.count();
        BigDecimal totalAreaAcres = landParcelRepository.findAll().stream()
                .map(LandParcel::getTotalAreaAcres)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        long activeCropsCount = cropRepository.findAll().stream()
                .filter(c -> !"HARVESTED".equalsIgnoreCase(c.getStatus()))
                .count();

        long totalActivities = recordRepository.count();
        long availableResources = resourceRepository.count();
        long activeAnnouncements = announcementRepository.count();

        List<CropDto> recentCrops = cropService.getAllCrops().stream().limit(5).collect(Collectors.toList());
        List<AnnouncementDto> recentAnnouncements = announcementService.getAllAnnouncements().stream().limit(4).collect(Collectors.toList());
        List<CultivationRecordDto> recentActivities = recordService.getAllRecords().stream().limit(6).collect(Collectors.toList());

        return DashboardStatsDto.builder()
                .totalFarmers(totalFarmers)
                .totalLandParcels(totalLandParcels)
                .totalAreaAcres(totalAreaAcres)
                .activeCropsCount(activeCropsCount)
                .totalCultivationActivities(totalActivities)
                .availableResourcesCount(availableResources)
                .activeAnnouncementsCount(activeAnnouncements)
                .recentCrops(recentCrops)
                .recentAnnouncements(recentAnnouncements)
                .recentActivities(recentActivities)
                .build();
    }

    @Override
    public DashboardStatsDto getFarmerStats(Long userId) {
        List<LandParcel> userParcels = landParcelRepository.findByUserId(userId);
        long totalLandParcels = userParcels.size();
        BigDecimal totalAreaAcres = userParcels.stream()
                .map(LandParcel::getTotalAreaAcres)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        List<CropDto> userCrops = cropService.getCropsByUserId(userId);
        long activeCropsCount = userCrops.stream()
                .filter(c -> !"HARVESTED".equalsIgnoreCase(c.getStatus()))
                .count();

        List<AnnouncementDto> recentAnnouncements = announcementService.getAllAnnouncements().stream().limit(4).collect(Collectors.toList());

        return DashboardStatsDto.builder()
                .totalFarmers(1)
                .totalLandParcels(totalLandParcels)
                .totalAreaAcres(totalAreaAcres)
                .activeCropsCount(activeCropsCount)
                .totalCultivationActivities(0)
                .availableResourcesCount(resourceRepository.count())
                .activeAnnouncementsCount(announcementRepository.count())
                .recentCrops(userCrops)
                .recentAnnouncements(recentAnnouncements)
                .build();
    }
}

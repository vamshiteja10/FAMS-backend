package com.farmassist.dto;

import lombok.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardStatsDto {
    private long totalFarmers;
    private long totalLandParcels;
    private BigDecimal totalAreaAcres;
    private long activeCropsCount;
    private long totalCultivationActivities;
    private long availableResourcesCount;
    private long activeAnnouncementsCount;

    // Recent items for rapid rendering
    private List<CropDto> recentCrops;
    private List<AnnouncementDto> recentAnnouncements;
    private List<CultivationRecordDto> recentActivities;
}

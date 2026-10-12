package com.farmassist.service;

import com.farmassist.dto.DashboardStatsDto;

public interface DashboardService {
    DashboardStatsDto getOverallStats();
    DashboardStatsDto getFarmerStats(Long userId);
}

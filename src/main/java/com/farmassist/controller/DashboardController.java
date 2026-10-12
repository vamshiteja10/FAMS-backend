package com.farmassist.controller;

import com.farmassist.dto.DashboardStatsDto;
import com.farmassist.service.DashboardService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/dashboard")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class DashboardController {

    private final DashboardService dashboardService;

    @GetMapping("/stats")
    public ResponseEntity<DashboardStatsDto> getOverallStats() {
        return ResponseEntity.ok(dashboardService.getOverallStats());
    }

    @GetMapping("/farmer/{userId}")
    public ResponseEntity<DashboardStatsDto> getFarmerStats(@PathVariable Long userId) {
        return ResponseEntity.ok(dashboardService.getFarmerStats(userId));
    }
}

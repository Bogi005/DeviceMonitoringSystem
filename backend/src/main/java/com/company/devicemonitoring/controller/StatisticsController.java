package com.company.devicemonitoring.controller;

import com.company.devicemonitoring.dto.DeviceStatisticsResponse;
import com.company.devicemonitoring.dto.SystemStatisticsResponse;
import com.company.devicemonitoring.service.StatisticsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stats")
public class StatisticsController {
    private final StatisticsService statisticsService;
    public StatisticsController(StatisticsService statisticsService) {
        this.statisticsService = statisticsService;
    }

    @GetMapping
    public ResponseEntity<SystemStatisticsResponse> getSystemStatistics() {
        return ResponseEntity.ok(statisticsService.getSystemStatistics());
    }

    @GetMapping("/{deviceId}")
    public ResponseEntity<DeviceStatisticsResponse> getDeviceStatistics(
            @PathVariable Long deviceId
    ) {
        return ResponseEntity.ok(statisticsService.getDeviceStatistics(deviceId));
    }
}

package com.company.devicemonitoring.controller;

import com.company.devicemonitoring.dto.AlarmResponse;
import com.company.devicemonitoring.service.AlarmService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/alarms")
public class AlarmController {
    private final AlarmService alarmService;
    public AlarmController(AlarmService alarmService) {
        this.alarmService = alarmService;
    }

    // GET /api/alarms?resolved=false&page=0&size=10&sort=createdAt,desc
    @GetMapping
    public ResponseEntity<Page<AlarmResponse>> getAllAlarms(
            @RequestParam(required = false) Boolean resolved,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ) {
        return ResponseEntity.ok(alarmService.getAllAlarms(resolved, pageable));
    }

    @GetMapping("/devices/{deviceId}")
    public ResponseEntity<Page<AlarmResponse>> getAlarmsByDeviceId(
            @PathVariable Long deviceId,
            @RequestParam(required = false) Boolean resolved,
            @PageableDefault(size = 10, sort = "createdAt", direction = Sort.Direction.DESC) Pageable pageable
            ) {
        return ResponseEntity.ok(alarmService.getAlarmsByDeviceId(deviceId, resolved, pageable));
    }

    @PatchMapping("/devices/{alarmId}")
    public ResponseEntity<AlarmResponse> resolveAlarm(
            @PathVariable Long alarmId
            ){
        return ResponseEntity.ok(alarmService.resolveAlarm(alarmId));
    }
}

package com.company.devicemonitoring.controller;

import com.company.devicemonitoring.dto.ReadingRequest;
import com.company.devicemonitoring.dto.ReadingResponse;
import com.company.devicemonitoring.service.ReadingService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/devices/{deviceId}/readings")
public class ReadingController {
    private final ReadingService readingService;

    public ReadingController(ReadingService readingService) {
        this.readingService = readingService;
    }

    // POST /api/devices/{deviceId}/readings
    // Insert reading
    @PostMapping
    public ResponseEntity<ReadingResponse> addReading(
            @PathVariable Long deviceId,
            @Valid @RequestBody ReadingRequest request
    ) {
        ReadingResponse createdReading = readingService.addReading(deviceId, request);
        return new ResponseEntity<>(createdReading, HttpStatus.CREATED);
    }

    // GET /api/devices/{deviceId}/readings/all
    // Show device readings
    @GetMapping
    public ResponseEntity<List<ReadingResponse>> getReadingsByDeviceId(
            @PathVariable Long deviceId
    ){
        List<ReadingResponse> readings = readingService.getReadingsByDeviceId(deviceId);
        return ResponseEntity.ok(readings);
    }
}

package com.company.devicemonitoring.controller;

import com.company.devicemonitoring.dto.ReadingRequest;
import com.company.devicemonitoring.dto.ReadingResponse;
import com.company.devicemonitoring.service.ReadingService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

    // GET /api/devices/{deviceId}/readings
    // Show device readings
    @GetMapping("/all")
    public ResponseEntity<List<ReadingResponse>> getReadingsByDeviceId(
            @PathVariable Long deviceId
    ){
        List<ReadingResponse> readings = readingService.getReadingsByDeviceId(deviceId);
        return ResponseEntity.ok(readings);
    }

    @GetMapping
    public ResponseEntity<Page<ReadingResponse>> getSortedReadingsByDeviceId(
            @PathVariable Long deviceId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "timestamp,desc") String[] sort
    ){
        Sort.Direction direction = sort[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<ReadingResponse> readings = readingService.getSortedReadings(deviceId, pageable);
        return ResponseEntity.ok(readings);
    }
}

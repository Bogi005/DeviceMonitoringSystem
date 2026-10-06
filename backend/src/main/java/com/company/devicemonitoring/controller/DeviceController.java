package com.company.devicemonitoring.controller;

import com.company.devicemonitoring.dto.DeviceRequest;
import com.company.devicemonitoring.dto.DeviceResponse;
import com.company.devicemonitoring.service.DeviceService;
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
@RequestMapping("/api/devices")
public class DeviceController {
    private final DeviceService deviceService;
    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    // Post /api/devices
    // Create device
    @PostMapping
    public ResponseEntity<DeviceResponse> createDevice(
            @Valid @RequestBody DeviceRequest request
    ){
        DeviceResponse createdDevice = deviceService.createDevice(request);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    // Search for devices
    @GetMapping
    public ResponseEntity<Page<DeviceResponse>> getDevices(
            @RequestParam(defaultValue = "") String serialNumber,
            @RequestParam(defaultValue = "") String location,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id,asc") String[] sort
    ){
        Sort.Direction direction = sort[1].equalsIgnoreCase("asc") ? Sort.Direction.ASC : Sort.Direction.DESC;
        Pageable  pageable = PageRequest.of(page, size, Sort.by(direction, sort[0]));
        Page<DeviceResponse> devices = deviceService.searchDevices(serialNumber, location, pageable);
        return ResponseEntity.ok(devices);
    }

    // GET /api/devices
    // Show all devices
    @GetMapping("/all")
    public ResponseEntity<List<DeviceResponse>> getAllDevices(){
        List<DeviceResponse> devices = deviceService.getAllDevices();
        return ResponseEntity.ok(devices);
    }

    // Get /api/devices/{id}
    // Show one device
    @GetMapping("/{id}")
    public ResponseEntity<DeviceResponse> getDeviceById(
            @PathVariable Long id
    ){
        DeviceResponse device = deviceService.getDeviceById(id);
        return ResponseEntity.ok(device);
    }

    // PUT /api/devices/{id}
    // Update device
    @PutMapping("/{id}")
    public ResponseEntity<DeviceResponse> updateDeviceById(
            @PathVariable Long id,
            @Valid @RequestBody DeviceRequest request
    ){
        DeviceResponse updatedDevice = deviceService.updateDeviceById(id, request);
        return ResponseEntity.ok(updatedDevice);
    }

    // Delete /api/devices/{id}
    // Delete one device
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDeviceById(
            @PathVariable Long id
    ){
        deviceService.deleteDeviceById(id);
        return ResponseEntity.noContent().build();
    }
}

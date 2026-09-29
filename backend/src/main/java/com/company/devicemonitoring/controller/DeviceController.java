package com.company.devicemonitoring.controller;

import com.company.devicemonitoring.dto.DeviceRequest;
import com.company.devicemonitoring.dto.DeviceResponse;
import com.company.devicemonitoring.service.DeviceService;
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
            @RequestBody DeviceRequest request
    ){
        DeviceResponse createdDevice = deviceService.createDevice(request);
        return new ResponseEntity<>(createdDevice, HttpStatus.CREATED);
    }

    // GET /api/devices
    // Show all devices
    @GetMapping
    public ResponseEntity<List<DeviceResponse>> getAllDevices(
    ){
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

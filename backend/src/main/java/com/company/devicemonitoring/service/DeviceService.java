package com.company.devicemonitoring.service;

import com.company.devicemonitoring.dto.DeviceRequest;
import com.company.devicemonitoring.dto.DeviceResponse;
import com.company.devicemonitoring.entity.Device;
import com.company.devicemonitoring.exception.DeviceNotFoundException;
import com.company.devicemonitoring.repository.DeviceRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeviceService {
    private final DeviceRepository deviceRepository;
    public DeviceService(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

    // Create
    public DeviceResponse createDevice(DeviceRequest request) {
        Device device = new Device(
                request.getSerialNumber(),
                request.getName(),
                request.getLocation()
        );

        Device savedDevice = deviceRepository.save(device);

        return mapToResponse(savedDevice);
    }

    // Get all
    public List<DeviceResponse> getAllDevices() {
        return deviceRepository.findAll()
                            .stream()
                            .map(this::mapToResponse)
                            .toList();
    }

    // Get by id
    public DeviceResponse getDeviceById(Long id) {
        Device device = deviceRepository.findById(id)
                .orElseThrow(() -> new DeviceNotFoundException("Device with id: " + id + " not found"));
        return mapToResponse(device);
    }

    // Delete
    public void deleteDeviceById(Long id) {
        if (!deviceRepository.existsById(id)) {
            throw new DeviceNotFoundException("Device with id: " + id + " not found");
        }
        deviceRepository.deleteById(id);
    }

    // Entity -> DTO
    private DeviceResponse mapToResponse(Device device){
        return new DeviceResponse(
                device.getId(),
                device.getSerialNumber(),
                device.getName(),
                device.getLocation()
        );
    }
}

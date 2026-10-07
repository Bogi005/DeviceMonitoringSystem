package com.company.devicemonitoring.service;

import com.company.devicemonitoring.dto.DeviceRequest;
import com.company.devicemonitoring.dto.DeviceResponse;
import com.company.devicemonitoring.entity.Device;
import com.company.devicemonitoring.exception.DeviceNotFoundException;
import com.company.devicemonitoring.repository.DeviceRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
                request.getSerialNumber().toUpperCase(),
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

    // Get Page
    public Page<DeviceResponse> searchDevices(String serialNumber, String location, Pageable pageable) {
        Page<Device> devicePage = deviceRepository.findBySerialNumberContainingIgnoreCaseAndLocationContainingIgnoreCase(
                serialNumber, location, pageable
        );
        return devicePage.map(this::mapToResponse);
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

    // Update
    public DeviceResponse updateDeviceById(Long id, DeviceRequest request) {
        Device updated = deviceRepository.findById(id).orElseThrow(() -> new DeviceNotFoundException("Device with id: " + id + " not found"));
        updated.setSerialNumber(request.getSerialNumber());
        updated.setName(request.getName());
        updated.setLocation(request.getLocation());
        Device savedDevice = deviceRepository.save(updated);
        return mapToResponse(savedDevice);
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

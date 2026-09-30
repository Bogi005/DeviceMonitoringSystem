package com.company.devicemonitoring;

import com.company.devicemonitoring.dto.DeviceRequest;
import com.company.devicemonitoring.dto.DeviceResponse;
import com.company.devicemonitoring.entity.Device;
import com.company.devicemonitoring.exception.DeviceNotFoundException;
import com.company.devicemonitoring.repository.DeviceRepository;
import com.company.devicemonitoring.service.DeviceService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class DeviceServiceTest {
    @Mock
    private DeviceRepository deviceRepository;

    @InjectMocks
    private DeviceService deviceService;

    @Test
    void createDevice() {
        // Given, when, then structure
        // GIVEN
        DeviceRequest deviceRequest = new DeviceRequest("DEV-001", "Thermometer", "Sarajevo");
        Device savedDevice = new Device("DEV-001", "Thermometer", "Sarajevo");
        savedDevice.setId(1L);
        when(deviceRepository.save(any(Device.class))).thenReturn(savedDevice);

        // WHEN
        DeviceResponse response = deviceService.createDevice(deviceRequest);

        // THEN
        assertNotNull(response);
        assertEquals(1L, response.getId());
        assertEquals("DEV-001", response.getSerialNumber());
        assertEquals("Thermometer", response.getName());
        assertEquals("Sarajevo", response.getLocation());
        // Check if saving device was called once
        verify(deviceRepository, times(1)).save(any(Device.class));
    }

    @Test
    void getDeviceById_NonExistent() {
        // GIVEN
        Long id = 999L;
        when(deviceRepository.findById(id)).thenReturn(Optional.empty());

        // WHEN
        DeviceNotFoundException exception = assertThrows(
                DeviceNotFoundException.class,
                () -> deviceService.getDeviceById(id)
        );

        // THEN
        assertEquals("Device with id: " + id + " not found", exception.getMessage());
        verify(deviceRepository, times(1)).findById(id);
    }
}

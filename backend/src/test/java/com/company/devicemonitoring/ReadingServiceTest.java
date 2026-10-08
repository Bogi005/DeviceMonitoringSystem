package com.company.devicemonitoring;

import com.company.devicemonitoring.dto.ReadingRequest;
import com.company.devicemonitoring.dto.ReadingResponse;
import com.company.devicemonitoring.entity.Device;
import com.company.devicemonitoring.entity.Reading;
import com.company.devicemonitoring.exception.ResourceNotFoundException;
import com.company.devicemonitoring.repository.DeviceRepository;
import com.company.devicemonitoring.repository.ReadingRepository;
import com.company.devicemonitoring.service.ReadingService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ReadingServiceTest {
    @Mock
    private DeviceRepository deviceRepository;
    @Mock
    private ReadingRepository readingRepository;
    @InjectMocks
    private ReadingService readingService;

    @Test
    void addReading()
    {
        // GIVEN
        Long deviceId = 1L;
        Device device = new Device("DEV-001", "Barometer", "Belgrade");
        device.setId(deviceId);
        ReadingRequest readingRequest = new ReadingRequest(1023.4);
        Reading savedReading = new Reading(readingRequest.getValue(), LocalDateTime.now(), device);
        savedReading.setId(10L);

        when(deviceRepository.findById(deviceId)).thenReturn(Optional.of(device));
        when(readingRepository.save(any(Reading.class))).thenReturn(savedReading);

        // WHEN
        ReadingResponse response = readingService.addReading(deviceId, readingRequest);

        // THEN
        assertNotNull(response);
        assertEquals(10L, response.getId());
        assertEquals(1023.4, response.getValue());

        verify(deviceRepository, times(1)).findById(deviceId);
        verify(readingRepository, times(1)).save(any(Reading.class));
    }

    @Test
    void addReading_NonExistentDevice() {
        // Given
        Long nonExistentDeviceId = 99L;
        ReadingRequest request = new ReadingRequest(22.5);

        when(deviceRepository.findById(nonExistentDeviceId)).thenReturn(Optional.empty());

        // When & Then
        assertThrows(
                ResourceNotFoundException.class,
                () -> readingService.addReading(nonExistentDeviceId, request)
        );

        verify(readingRepository, never()).save(any());
    }
}

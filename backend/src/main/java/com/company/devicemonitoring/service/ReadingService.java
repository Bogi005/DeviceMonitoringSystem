package com.company.devicemonitoring.service;

import com.company.devicemonitoring.dto.ReadingRequest;
import com.company.devicemonitoring.dto.ReadingResponse;
import com.company.devicemonitoring.entity.Device;
import com.company.devicemonitoring.entity.Reading;
import com.company.devicemonitoring.exception.DeviceNotFoundException;
import com.company.devicemonitoring.repository.DeviceRepository;
import com.company.devicemonitoring.repository.ReadingRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ReadingService {
    private final ReadingRepository readingRepository;
    private final DeviceRepository deviceRepository;

    public ReadingService(
            ReadingRepository readingRepository,
            DeviceRepository deviceRepository
    ) {
        this.readingRepository = readingRepository;
        this.deviceRepository = deviceRepository;
    }

    // Add new reading for device
    public ReadingResponse addReading(
            Long deviceId,
            ReadingRequest request
    ) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceNotFoundException("Device with id: " + deviceId + " not found"));
        Reading reading = new Reading(
                request.getValue(),
                LocalDateTime.now(),
                device
        );

        Reading savedReading = readingRepository.save(reading);

        return new ReadingResponse(
                savedReading.getId(),
                savedReading.getValue(),
                savedReading.getTimestamp()
        );
    }

    // Get sorted page
    public Page<ReadingResponse> getSortedReadings(Long deviceId, Pageable pageable) {
        Page<Reading> readingPage = readingRepository.findByDeviceId(deviceId, pageable);
        return readingPage.map(
                reading -> new ReadingResponse(
                        reading.getId(),
                        reading.getValue(),
                        reading.getTimestamp()
                )
        );
    }

    // Get device reading history
    public List<ReadingResponse> getReadingsByDeviceId(Long deviceId) {
        if (!deviceRepository.existsById(deviceId)) {
            throw new DeviceNotFoundException("Device with id: " + deviceId + " not found");
        }
        return readingRepository.findByDeviceIdOrderByTimestampDesc(deviceId)
                .stream().map(reading -> new ReadingResponse(
                        reading.getId(),
                        reading.getValue(),
                        reading.getTimestamp()
                )).toList();
    }
}

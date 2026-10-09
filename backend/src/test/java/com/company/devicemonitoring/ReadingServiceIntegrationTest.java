package com.company.devicemonitoring;

import com.company.devicemonitoring.dto.ReadingRequest;
import com.company.devicemonitoring.dto.SystemStatisticsResponse;
import com.company.devicemonitoring.entity.Device;
import com.company.devicemonitoring.repository.AlarmRepository;
import com.company.devicemonitoring.repository.DeviceRepository;
import com.company.devicemonitoring.repository.ReadingRepository;
import com.company.devicemonitoring.service.ReadingService;
import com.company.devicemonitoring.service.StatisticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Testcontainers
public class ReadingServiceIntegrationTest {
    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:16-alpine");

    @Autowired
    private ReadingService readingService;

    @Autowired
    private StatisticsService statisticsService;

    @Autowired
    private DeviceRepository deviceRepository;

    @Autowired
    private ReadingRepository readingRepository;

    @Autowired
    private AlarmRepository alarmRepository;

    private Device savedDevice;

    @BeforeEach
    public void setup() {
        alarmRepository.deleteAll();
        readingRepository.deleteAll();
        deviceRepository.deleteAll();

        Device device = new Device();
        device.setName("device1");
        device.setSerialNumber("DEV-000");
        device.setLocation("Prizren");
        savedDevice = deviceRepository.save(device);
    }

    @Test
    @DisplayName("Integration test: Save reading and generate alarm.")
    public void saveReadingAndGenerateAlarm() {
        // GIVEN
        ReadingRequest request = new ReadingRequest();
        request.setValue(150.0);

        // WHEN
        readingService.addReading(savedDevice.getId(), request);

        // THEN
        assertEquals(1, readingRepository.count(), "There should be only one reading.");
        assertEquals(1, alarmRepository.count(), "There should be only one alarm.");

        SystemStatisticsResponse stats = statisticsService.getSystemStatistics();
        assertEquals(1, stats.getTotalDeviceCount(), "There should be only one device.");
        assertEquals(1, stats.getTotalReadingCount(), "There should be only one reading.");
        assertEquals(1, stats.getTotalUnresolvedAlarmCount(), "There should be only one unresolved alarm.");
        assertEquals(150.0, stats.getDeviceStatistics().getFirst().getMaxValue());
    }

    @Test
    @DisplayName("Integration test: Device with no readings.")
    public void deviceWithNoReadings() {
        // GIVEN

        // WHEN
        SystemStatisticsResponse stats = statisticsService.getSystemStatistics();

        // THEN
        assertEquals(1, stats.getTotalDeviceCount(), "There should be only one device.");
        assertEquals(0, stats.getTotalReadingCount(), "There should be no readings.");
        assertEquals(1, stats.getDevicesWithoutReadings().size());
        assertEquals("DEV-000", stats.getDevicesWithoutReadings().getFirst());
    }
}

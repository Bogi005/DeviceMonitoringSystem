package com.company.devicemonitoring.service;

import com.company.devicemonitoring.dto.DeviceStatisticsResponse;
import com.company.devicemonitoring.dto.SystemStatisticsResponse;
import com.company.devicemonitoring.exception.ResourceNotFoundException;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class StatisticsService {
    private final JdbcClient jdbcClient;
    public StatisticsService(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Transactional(readOnly = true)
    public SystemStatisticsResponse getSystemStatistics() {
        String deviceStatisticsSQL = """
                SELECT
                    d.id AS device_id,
                    d.name AS device_name,
                    COUNT(DISTINCT r.id) AS reading_count,
                    COALESCE(ROUND(AVG(r.value)::numeric, 2), 0) AS avg_reading_value,
                    COALESCE(MAX(r.value), 0) AS max_reading_value,
                    COUNT(DISTINCT CASE WHEN a.resolved = FALSE THEN a.id END) AS active_alarm_count
                FROM devices d
                LEFT JOIN readings r ON d.id = r.device_id
                LEFT JOIN alarms a  ON d.id = a.device_id
                GROUP BY d.id, d.name
                ORDER BY d.id
        """;

        List<DeviceStatisticsResponse> deviceStats = jdbcClient.sql(deviceStatisticsSQL)
                .query((res, rowNum) ->
                    new DeviceStatisticsResponse(
                            res.getLong("device_id"),
                            res.getString("device_name"),
                            res.getLong("reading_count"),
                            res.getDouble("avg_reading_value"),
                            res.getDouble("max_reading_value"),
                            res.getLong("active_alarm_count")
                    )
                ).list();

        String devicesWithoutReadingSQL = """
                SELECT
                    d.name
                FROM devices d
                LEFT JOIN readings r ON d.id = r.device_id
                WHERE r.id IS NULL
                ORDER BY d.name
        """;

        List<String> devicesWithoutReadings = jdbcClient.sql(devicesWithoutReadingSQL)
                .query(String.class)
                .list();

        Long totalDevices = jdbcClient.sql("SELECT COUNT(*) FROM devices").query(Long.class).single();
        Long totalReadings = jdbcClient.sql("SELECT COUNT(*) FROM readings").query(Long.class).single();
        Long totalUnresolvedAlarms = jdbcClient.sql("SELECT COUNT(*) FROM alarms WHERE resolved = FALSE").query(Long.class).single();

        return new SystemStatisticsResponse(
                totalDevices,
                totalReadings,
                totalUnresolvedAlarms,
                devicesWithoutReadings,
                deviceStats
        );
    }

    @Transactional(readOnly = true)
    public DeviceStatisticsResponse getDeviceStatistics(Long deviceId) {
        String deviceStatisticsSQL = """
                SELECT
                    d.id AS device_id,
                    d.name AS device_name,
                    COUNT(DISTINCT r.id) AS reading_count,
                    COALESCE(ROUND(AVG(r.value)::numeric, 2), 0) AS avg_reading_value,
                    COALESCE(MAX(r.value), 0) AS max_reading_value,
                    COUNT(DISTINCT CASE WHEN a.resolved = FALSE THEN a.id END) AS active_alarm_count
                FROM devices d
                LEFT JOIN readings r ON d.id = r.device_id
                LEFT JOIN alarms a  ON d.id = a.device_id
                WHERE d.id = :deviceId
                GROUP BY d.id, d.name
                ORDER BY d.id
        """;

        return jdbcClient.sql(deviceStatisticsSQL)
                .param("deviceId", deviceId)
                .query((res, rowNum) ->
                        new DeviceStatisticsResponse(
                                res.getLong("device_id"),
                                res.getString("device_name"),
                                res.getLong("reading_count"),
                                res.getDouble("avg_reading_value"),
                                res.getDouble("max_reading_value"),
                                res.getLong("active_alarm_count")
                        )
                ).optional().orElseThrow(() ->
                        new ResourceNotFoundException("Device with id: " + deviceId + " not found!")
                );
    }
}

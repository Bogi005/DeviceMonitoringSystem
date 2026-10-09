package com.company.devicemonitoring.dto;

import java.util.List;

public class SystemStatisticsResponse {
    Long totalDeviceCount;
    Long totalReadingCount;
    Long totalUnresolvedAlarmCount;
    List<String> devicesWithoutReadings;
    List<DeviceStatisticsResponse> deviceStatistics;

    public SystemStatisticsResponse() {
    }

    public Long getTotalDeviceCount() {
        return totalDeviceCount;
    }

    public void setTotalDeviceCount(Long totalDeviceCount) {
        this.totalDeviceCount = totalDeviceCount;
    }

    public Long getTotalReadingCount() {
        return totalReadingCount;
    }

    public void setTotalReadingCount(Long totalReadingCount) {
        this.totalReadingCount = totalReadingCount;
    }

    public Long getTotalUnresolvedAlarmCount() {
        return totalUnresolvedAlarmCount;
    }

    public void setTotalUnresolvedAlarmCount(Long totalUnresolvedAlarmCount) {
        this.totalUnresolvedAlarmCount = totalUnresolvedAlarmCount;
    }

    public List<String> getDevicesWithoutReadings() {
        return devicesWithoutReadings;
    }

    public void setDevicesWithoutReadings(List<String> devicesWithoutReadings) {
        this.devicesWithoutReadings = devicesWithoutReadings;
    }

    public List<DeviceStatisticsResponse> getDeviceStatistics() {
        return deviceStatistics;
    }

    public void setDeviceStatistics(List<DeviceStatisticsResponse> deviceStatistics) {
        this.deviceStatistics = deviceStatistics;
    }
}

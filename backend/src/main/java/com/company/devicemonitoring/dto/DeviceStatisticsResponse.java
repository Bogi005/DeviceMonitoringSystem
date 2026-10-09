package com.company.devicemonitoring.dto;

public class DeviceStatisticsResponse {
    Long deviceId;
    String deviceName;
    Long readingCount;
    Double avgValue;
    Double maxValue;
    Long unresolvedAlarmCount;

    public DeviceStatisticsResponse() {
    }

    public DeviceStatisticsResponse(Long deviceId, String deviceName, Long readingCount, Double avgValue, Double maxValue, Long unresolvedAlarmCount) {
        this.deviceId = deviceId;
        this.deviceName = deviceName;
        this.readingCount = readingCount;
        this.avgValue = avgValue;
        this.maxValue = maxValue;
        this.unresolvedAlarmCount = unresolvedAlarmCount;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public Long getReadingCount() {
        return readingCount;
    }

    public void setReadingCount(Long readingCount) {
        this.readingCount = readingCount;
    }

    public Double getAvgValue() {
        return avgValue;
    }

    public void setAvgValue(Double avgValue) {
        this.avgValue = avgValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public Long getUnresolvedAlarmCount() {
        return unresolvedAlarmCount;
    }

    public void setUnresolvedAlarmCount(Long unresolvedAlarmCount) {
        this.unresolvedAlarmCount = unresolvedAlarmCount;
    }
}

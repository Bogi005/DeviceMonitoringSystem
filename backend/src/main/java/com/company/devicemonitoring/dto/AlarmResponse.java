package com.company.devicemonitoring.dto;

import com.company.devicemonitoring.entity.Alarm;

import java.time.LocalDateTime;

public class AlarmResponse {
    private Long id;
    private String alarmType;
    private String message;
    private LocalDateTime alarmTime;
    private Boolean resolved;

    public AlarmResponse() {}
    public AlarmResponse(Long id, String alarmType, String message, LocalDateTime alarmTime, Boolean resolved) {
        this.id = id;
        this.alarmType = alarmType;
        this.message = message;
        this.alarmTime = alarmTime;
        this.resolved = resolved;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(String alarmType) {
        this.alarmType = alarmType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getAlarmTime() {
        return alarmTime;
    }

    public void setAlarmTime(LocalDateTime alarmTime) {
        this.alarmTime = alarmTime;
    }

    public Boolean getResolved() {
        return resolved;
    }

    public void setResolved(Boolean resolved) {
        this.resolved = resolved;
    }
}

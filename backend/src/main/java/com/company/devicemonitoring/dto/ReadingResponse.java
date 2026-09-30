package com.company.devicemonitoring.dto;

import java.time.LocalDateTime;

public class ReadingResponse {
    private Long id;
    private Double value;
    private LocalDateTime timestamp;

    public ReadingResponse() {}
    public ReadingResponse(Long id,
                           Double value,
                           LocalDateTime timestamp) {
        this.id = id;
        this.value = value;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

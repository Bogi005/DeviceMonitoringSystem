package com.company.devicemonitoring.dto;

import jakarta.validation.constraints.NotNull;

public class ReadingRequest {
    @NotNull(message = "Value is required")
    private Double value;

    public ReadingRequest() {}
    public ReadingRequest(Double value) {
        this.value = value;
    }

    public Double getValue() {
        return value;
    }
    public void setValue(Double value) {
        this.value = value;
    }
}

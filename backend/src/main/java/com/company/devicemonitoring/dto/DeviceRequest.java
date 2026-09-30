package com.company.devicemonitoring.dto;

import jakarta.validation.constraints.NotBlank;

public class DeviceRequest {
    @NotBlank(message = "Serial number is required")
    private String serialNumber;

    @NotBlank(message = "Name is required")
    private String name;

    @NotBlank(message = "Location is required")
    private String location;

    public DeviceRequest(){}

    public DeviceRequest(String serialNumber, String name, String location) {
        this.serialNumber = serialNumber;
        this.name = name;
        this.location = location;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}

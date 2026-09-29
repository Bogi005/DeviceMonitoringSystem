package com.company.devicemonitoring.dto;

public class DeviceRequest {
    private String serialNumber;
    private String name;
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

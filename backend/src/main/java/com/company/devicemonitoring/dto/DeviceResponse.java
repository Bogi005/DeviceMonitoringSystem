package com.company.devicemonitoring.dto;

public class DeviceResponse {

    private Long id;
    private String serialNumber;
    private String name;
    private String location;

    public DeviceResponse() {
    }

    public DeviceResponse(Long id, String serialNumber, String name, String location) {
        this.id = id;
        this.serialNumber = serialNumber;
        this.name = name;
        this.location = location;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
package com.company.devicemonitoring.repository;

import com.company.devicemonitoring.entity.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsBySerialNumber(String serialNumber);
}

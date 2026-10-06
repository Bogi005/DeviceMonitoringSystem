package com.company.devicemonitoring.repository;

import com.company.devicemonitoring.entity.Device;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    boolean existsBySerialNumber(String serialNumber);
    Page<Device> findBySerialNumber(String serialNumber, Pageable pageable);
    Page<Device> findByLocation(String location, Pageable pageable);
    Page<Device> findBySerialNumberContainingIgnoreCaseAndLocationContainingIgnoreCase(
            String serialNumber,
            String location,
            Pageable pageable
    );
}

package com.company.devicemonitoring.repository;

import com.company.devicemonitoring.entity.Reading;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReadingRepository extends JpaRepository<Reading, Long> {
    List<Reading> findByDeviceIdOrderByTimestampDesc(Long deviceId);
}

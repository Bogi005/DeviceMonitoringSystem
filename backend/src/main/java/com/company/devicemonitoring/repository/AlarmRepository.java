package com.company.devicemonitoring.repository;

import com.company.devicemonitoring.entity.Alarm;
import com.company.devicemonitoring.entity.AlarmType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlarmRepository extends JpaRepository<Alarm, Long>{
    Page<Alarm> findByDeviceId(Long deviceId, Pageable pageable);
    Page<Alarm> findByResolved(Boolean resolved, Pageable pageable);
    Page<Alarm> findByDeviceIdAndResolved(Long deviceId, Boolean resolved, Pageable pageable);
    Page<Alarm> findByDeviceIdAndAlarmType(Long device_id, AlarmType alarmType, Pageable pageable);
}

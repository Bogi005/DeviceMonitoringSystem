package com.company.devicemonitoring.service;

import com.company.devicemonitoring.dto.AlarmResponse;
import com.company.devicemonitoring.entity.Alarm;
import com.company.devicemonitoring.exception.ResourceNotFoundException;
import com.company.devicemonitoring.repository.AlarmRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlarmService {
    private final AlarmRepository alarmRepository;

    public AlarmService(AlarmRepository alarmRepository) {
        this.alarmRepository = alarmRepository;
    }

    @Transactional(readOnly = true)
    public Page<AlarmResponse> getAllAlarms(Boolean resolved, Pageable pageable) {
        Page<Alarm> alarms;
        if (resolved != null) {
            alarms = alarmRepository.findByResolved(resolved, pageable);
        }
        else {
            alarms = alarmRepository.findAll(pageable);
        }
        return alarms.map(this::mapToAlarmResponse);
    }

    @Transactional(readOnly = true)
    public Page<AlarmResponse> getAlarmsByDeviceId(Long deviceId, Boolean resolved, Pageable pageable) {
        Page<Alarm> alarms;
        if (resolved != null) {
            alarms = alarmRepository.findByDeviceIdAndResolved(deviceId, resolved, pageable);
        }
        else {
            alarms = alarmRepository.findByDeviceId(deviceId, pageable);
        }
        return alarms.map(this::mapToAlarmResponse);
    }

    @Transactional
    public AlarmResponse resolveAlarm(Long alarmId){
        Alarm alarm = alarmRepository.findById(alarmId)
                .orElseThrow(() -> new ResourceNotFoundException("Alarm with id: " + alarmId + " not found"));
        alarm.setResolved(true);
        Alarm savedAlarm = alarmRepository.save(alarm);
        return mapToAlarmResponse(savedAlarm);
    }

    private AlarmResponse mapToAlarmResponse(Alarm alarm) {
        return new AlarmResponse(
                alarm.getId(),
                alarm.getAlarmType().toString(),
                alarm.getMessage(),
                alarm.getCreatedAt(),
                alarm.isResolved()
        );
    }
}

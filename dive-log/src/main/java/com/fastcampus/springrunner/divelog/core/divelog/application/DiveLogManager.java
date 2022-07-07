package com.fastcampus.springrunner.divelog.core.divelog.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogDto;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogRegisterCommand;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogUpdateCommand;
import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLog;
import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLogNotFoundException;
import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLogRepository;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePointNotFoundException;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePointRepository;

@Service
public class DiveLogManager implements DiveLogFinder, DiveLogEditor {
    private final DiveLogRepository diveLogRepository;
    private final DivePointRepository divePointRepository;

    public DiveLogManager(DiveLogRepository diveLogRepository, DivePointRepository divePointRepository) {
        this.diveLogRepository = diveLogRepository;
        this.divePointRepository = divePointRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiveLogDto> findAll() {
        return diveLogRepository.findAll().stream()
                .map(DiveLogDto::ofEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional(readOnly = true)
    public List<DiveLogDto> findByDivePointId(Long divePointId) {
        DivePoint divePoint = divePointRepository.findById(divePointId)
                .orElseThrow(() -> new DivePointNotFoundException(divePointId));
        
        return diveLogRepository.findByDivePoint(divePoint).stream()
                .map(DiveLogDto::ofEntity)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DiveLogDto> findById(Long diveLogId) {
        return diveLogRepository.findById(diveLogId)
                .map(DiveLogDto::ofEntity);
    }

    @Override
    @Transactional
    public DiveLogDto save(DiveLogRegisterCommand registerCommand) {
        DivePoint divePoint = divePointRepository.findById(registerCommand.getDivePointId())
                .orElseThrow(() -> new DivePointNotFoundException(registerCommand.getDivePointId()));

        return DiveLogDto.ofEntity(diveLogRepository.save(registerCommand.convertToEntity(divePoint)));
    }

    @Override
    @Transactional
    public DiveLogDto update(Long diveLogId, DiveLogUpdateCommand updateCommand) {
        DiveLog diveLog = diveLogRepository.findById(diveLogId)
                .orElseThrow(() -> new DiveLogNotFoundException(diveLogId));
        
        return DiveLogDto.ofEntity(diveLogRepository.save(updateCommand.update(diveLog)));
    }

    @Override
    @Transactional
    public void delete(Long diveLogId) {
        DiveLog diveLog = diveLogRepository.findById(diveLogId)
                .orElseThrow(() -> new DiveLogNotFoundException(diveLogId));

        diveLogRepository.delete(diveLog);
    }
}

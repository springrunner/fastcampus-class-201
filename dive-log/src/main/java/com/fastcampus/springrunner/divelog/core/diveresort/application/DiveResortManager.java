package com.fastcampus.springrunner.divelog.core.diveresort.application;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.springrunner.divelog.common.log.TraceLog;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortDto;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortRegisterCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortUpdateCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortNotFoundException;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;

@TraceLog
@Service
public class DiveResortManager implements DiveResortEditor, DiveResortFinder {
    private final DiveResortRepository repository;

    public DiveResortManager(DiveResortRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DiveResortDto> findAll() {
        return repository.findAll().stream()
                .map(DiveResortDto::ofEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DiveResortDto> findByDiveResortId(Long diveResortId) {
//        throw new DiveResortNotFoundException(diveResortId);
        return repository.findById(diveResortId).map(DiveResortDto::ofEntity);
    }

    @Transactional
    @Override
    public DiveResortDto save(DiveResortRegisterCommand registCommand) {
        return DiveResortDto.ofEntity(repository.save(registCommand.convertToEntity()));
    }

    @Transactional
    @Override
    public DiveResortDto update(Long diveResortId, DiveResortUpdateCommand updateCommand) {
        DiveResort diveResort = repository.findById(diveResortId)
                .orElseThrow(() -> new DiveResortNotFoundException(diveResortId));
        
        return DiveResortDto.ofEntity(repository.save(updateCommand.update(diveResort)));
    }

    @Transactional
    @Override
    public void delete(Long diveResortId) {
        DiveResort diveResort = repository.findById(diveResortId)
                .orElseThrow(() -> new DiveResortNotFoundException(diveResortId));
        
        repository.delete(diveResort);
    }

}

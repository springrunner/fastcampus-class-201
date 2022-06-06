package com.fastcampus.springrunner.divelog.core.divelog.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogDto;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogRegisterCommand;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogUpdateCommand;

@Service
public class DiveLogManager implements DiveLogFinder, DiveLogEditor {

    @Override
    public DiveLogDto save(DiveLogRegisterCommand registerCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DiveLogDto update(Long diveLogId, DiveLogUpdateCommand updateCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long diveLogId) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public List<DiveLogDto> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DiveLogDto> findByDivePointId(Long divePointId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<DiveLogDto> findById(Long diveLogId) {
        // TODO Auto-generated method stub
        return null;
    }
    
}

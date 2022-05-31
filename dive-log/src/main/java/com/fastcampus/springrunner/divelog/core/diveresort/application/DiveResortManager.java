package com.fastcampus.springrunner.divelog.core.diveresort.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortDto;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortRegisterCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortUpdateCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

@Service
public class DiveResortManager implements DiveResortEditor, DiveResortFinder {

    @Transactional(readOnly = true)
    @Override
    public List<DiveResort> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DiveResort> findByDiveResortId(Long diveResortId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public DiveResortDto save(DiveResortRegisterCommand registCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public DiveResortDto update(DiveResortUpdateCommand updateCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public void delete(Long diveResortId) {
        // TODO Auto-generated method stub
        
    }

}

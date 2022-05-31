package com.fastcampus.springrunner.divelog.core.diveresort.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointDto;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointRegisterCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointUpdateCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;

@Service
public class DivePointManager implements DivePointEditor, DivePointFinder {

    @Transactional(readOnly = true)
    @Override
    public List<DivePoint> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public List<DivePoint> findByDiveResortId(Long diveResortId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<DivePoint> findByDivePointId(Long divePointId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public DivePointDto save(DivePointRegisterCommand registerCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public DivePointDto update(DivePointUpdateCommand updateCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Transactional
    @Override
    public void delete(Long divePointId) {
        // TODO Auto-generated method stub
        
    }

}

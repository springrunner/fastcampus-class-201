package com.fastcampus.springrunner.divelog.core.diveresort.application;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointDto;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointRegisterCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointUpdateCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;

@Service
public class DivePointManager implements DivePointEditor, DivePointFinder {

    @Override
    public List<DivePoint> findAll() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<DivePoint> findByDiveResortId(Long diveResortId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Optional<DivePoint> findByDivePointId(Long divePointId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DivePointDto save(DivePointRegisterCommand registerCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public DivePointDto update(DivePointUpdateCommand updateCommand) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Long divePointId) {
        // TODO Auto-generated method stub
        
    }

}

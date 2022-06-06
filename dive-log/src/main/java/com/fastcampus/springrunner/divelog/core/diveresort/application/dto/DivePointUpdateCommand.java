package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;

import lombok.Getter;

@Getter
public class DivePointUpdateCommand {
    private String name;
    private String depth;
    private String description;
    
    public static DivePointUpdateCommand create(String name, String depth, String description) {
        DivePointUpdateCommand updateCommand = new DivePointUpdateCommand();
        updateCommand.name = name;
        updateCommand.depth = depth;
        updateCommand.description = description;
        return updateCommand;
    }
    
    public DivePoint update(DivePoint divePoint) {
        divePoint.update(getName(), getDepth(), getDescription());
        return divePoint;
    }
}

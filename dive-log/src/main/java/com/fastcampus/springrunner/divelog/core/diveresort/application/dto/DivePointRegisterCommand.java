package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

import lombok.Getter;

@Getter
public class DivePointRegisterCommand {
    private Long diveResortId;
    private String name;
    private String depth;
    private String description;

    public static DivePointRegisterCommand create(Long diveResortId, String name, String depth, String description) {
        DivePointRegisterCommand registerCommand = new DivePointRegisterCommand();
        registerCommand.diveResortId = diveResortId;
        registerCommand.name = name;
        registerCommand.depth = depth;
        registerCommand.description = description;
        return registerCommand;
    }

    public DivePoint convertToEntity(DiveResort diveResort) {
        return DivePoint.create(diveResort, getName(), getDepth(), getDescription());
    }

}

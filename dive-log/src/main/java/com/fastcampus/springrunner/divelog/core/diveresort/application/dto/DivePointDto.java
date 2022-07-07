package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import java.time.LocalDateTime;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class DivePointDto {
    private Long id;
    private DiveResortDto diveResort;
    private String name;
    private String depth;
    private String description;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedDateTime;
    
    public static DivePointDto ofEntity(DivePoint divePoint) {
        DivePointDto divePointDto = new DivePointDto();
        divePointDto.id = divePoint.getId();
        divePointDto.diveResort = DiveResortDto.ofEntity(divePoint.getDiveResort());
        divePointDto.name = divePoint.getName();
        divePointDto.depth = divePoint.getDepth();
        divePointDto.description = divePoint.getDescription();
        divePointDto.createdDateTime = divePoint.getCreatedDateTime();
        divePointDto.lastModifiedDateTime = divePoint.getLastModifiedDateTime();
        return divePointDto;
    }
}

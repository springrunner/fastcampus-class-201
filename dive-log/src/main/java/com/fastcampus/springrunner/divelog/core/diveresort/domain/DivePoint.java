package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.time.LocalDateTime;

import lombok.Getter;

@Getter
public class DivePoint {
    private Long id;
    private DiveResort diveResort;
    private String name;
    private String depth;
    private String description;
    
    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    public static DivePoint create(
            DiveResort diveResort, 
            String name, 
            String depth, 
            String description) {
        
        DivePoint divePoint = new DivePoint();
        divePoint.diveResort = diveResort;
        divePoint.name = name;
        divePoint.depth = depth;
        divePoint.description = description;
        divePoint.createdDateTime = LocalDateTime.now();
        divePoint.lastModifiedDateTime = divePoint.createdDateTime;
        
        return divePoint;
    }
}

package com.fastcampus.springrunner.divelog.core.divelog.application.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLog;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Getter;

@Getter
public class DiveLogDto {
    private Long id;
    private Long diveResortId;
    private String diveResortName;
    private Long divePointId;
    private String divePointName;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate diveDate;
    @JsonFormat(pattern="'T'HH:mm:ss")
    private LocalTime entryTime;
    @JsonFormat(pattern="'T'HH:mm:ss")
    private LocalTime exitTime;
    private String weather;
    private String buddyName;
    private String comment;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime createdDateTime;
    @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime lastModifiedDateTime;
    
    public static DiveLogDto ofEntity(DiveLog entity) {
        DiveLogDto diveLogDto = new DiveLogDto();
        diveLogDto.id = entity.getId();
        DiveResort diveResort = entity.getDivePoint().getDiveResort();
        diveLogDto.diveResortId = diveResort.getId();
        diveLogDto.diveResortName = diveResort.getName();
        DivePoint divePoint = entity.getDivePoint();
        diveLogDto.divePointId = divePoint.getId();
        diveLogDto.divePointName = divePoint.getName();
        diveLogDto.diveDate = entity.getDiveDate();
        diveLogDto.entryTime = entity.getEntryTime();
        diveLogDto.exitTime = entity.getExitTime();
        diveLogDto.buddyName = entity.getBuddyName();
        diveLogDto.weather = entity.getWeather();
        diveLogDto.comment = entity.getComment();
        diveLogDto.createdDateTime = entity.getCreatedDateTime();
        diveLogDto.lastModifiedDateTime = entity.getLastModifiedDateTime();
        
        return diveLogDto;
    }
}

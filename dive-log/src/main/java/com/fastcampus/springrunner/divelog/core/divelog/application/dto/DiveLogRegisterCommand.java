package com.fastcampus.springrunner.divelog.core.divelog.application.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLog;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;

import lombok.Getter;

@Getter
public class DiveLogRegisterCommand {
    private Long divePointId;
    private LocalDate diveDate;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private String weather;
    private String buddyName;
    private String comment;

    /**
     * 
     * @param divePointId
     * @param diveDate
     * @param entryTime
     * @param exitTime
     * @param weather
     * @param buddyName
     * @param comment
     * @return
     */
    public static DiveLogRegisterCommand create(Long divePointId, LocalDate diveDate, LocalTime entryTime,
            LocalTime exitTime, String weather, String buddyName, String comment) {

        DiveLogRegisterCommand registerCommand = new DiveLogRegisterCommand();
        registerCommand.divePointId = divePointId;
        registerCommand.diveDate = diveDate;
        registerCommand.entryTime = entryTime;
        registerCommand.exitTime = exitTime;
        registerCommand.weather = weather;
        registerCommand.buddyName = buddyName;
        registerCommand.comment = comment;
        return registerCommand;
    }

    public DiveLog convertToEntity(DivePoint divePoint) {
        return DiveLog.create(divePoint, getDiveDate(), getEntryTime(), getExitTime(), getWeather(), getBuddyName(),
                getComment());
    }
}

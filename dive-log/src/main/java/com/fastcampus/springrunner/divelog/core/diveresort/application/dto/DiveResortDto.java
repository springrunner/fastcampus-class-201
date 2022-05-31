package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

import lombok.Getter;

@Getter
public class DiveResortDto {
    private Long id;
    private String name;
    private String location;
    private String ownerName;
    private String contactNumber;

    public static DiveResortDto ofEntity(DiveResort diveResort) {
        DiveResortDto diveResortDto = new DiveResortDto();
        diveResortDto.id = diveResort.getId();
        diveResortDto.name = diveResort.getName();
        diveResortDto.location = diveResort.getLocation();
        diveResortDto.ownerName = diveResort.getOwnerName();
        diveResortDto.contactNumber = diveResort.getContactNumber();
        return diveResortDto;
    }
}

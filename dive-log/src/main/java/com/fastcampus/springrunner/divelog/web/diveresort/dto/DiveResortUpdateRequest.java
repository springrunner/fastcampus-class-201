package com.fastcampus.springrunner.divelog.web.diveresort.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortUpdateCommand;

import lombok.Getter;

/**
 * 다이브리조트 갱신요청
 * 
 * @author springrunner.kr@gmail.com
 *
 */
@Getter
public class DiveResortUpdateRequest {
    //TODO validation 
    private String name;
    private String ownerName;
    private String contactNumber;
    private String address;
    private String description;
    
    public DiveResortUpdateCommand convertToUpdateCommand() {
        return DiveResortUpdateCommand.create(getName(), getOwnerName(), getContactNumber(), getAddress(), getDescription());
    }
}

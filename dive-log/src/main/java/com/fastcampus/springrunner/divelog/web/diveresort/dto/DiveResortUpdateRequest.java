package com.fastcampus.springrunner.divelog.web.diveresort.dto;

import javax.validation.constraints.NotEmpty;

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
    @NotEmpty
    private String name;
    @NotEmpty
    private String ownerName;
    @NotEmpty
    private String contactNumber;
    @NotEmpty
    private String address;
    @NotEmpty
    private String description;
    
    public DiveResortUpdateCommand convertToUpdateCommand() {
        return DiveResortUpdateCommand.create(getName(), getOwnerName(), getContactNumber(), getAddress(), getDescription());
    }
}

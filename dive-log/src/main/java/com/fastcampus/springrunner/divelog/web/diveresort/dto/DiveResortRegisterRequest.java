package com.fastcampus.springrunner.divelog.web.diveresort.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortRegisterCommand;

import lombok.Getter;

/**
 * 다이브리조트 등록요청
 * 
 * @author springrunner.kr@gmail.com
 *
 */
@Getter
public class DiveResortRegisterRequest {
    //TODO validation 
    private String name;
    private String location;
    private String ownerName;
    private String contactNumber;

    public DiveResortRegisterCommand convertToRegisterCommand() {
        return DiveResortRegisterCommand.create(getName(), getLocation(), getOwnerName(), getContactNumber());
    }
}

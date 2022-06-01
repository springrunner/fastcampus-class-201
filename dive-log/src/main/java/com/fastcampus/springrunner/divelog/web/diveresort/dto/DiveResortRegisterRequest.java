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
    private String name;
    private String ownerName;
    private String contactNumber;
    private String address;
    private String description;

    public DiveResortRegisterCommand convertToRegisterCommand() {
        return DiveResortRegisterCommand.create(getName(), getOwnerName(), getContactNumber(), getAddress(),
                getDescription());
    }
}

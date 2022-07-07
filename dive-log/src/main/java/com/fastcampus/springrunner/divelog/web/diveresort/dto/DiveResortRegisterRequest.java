package com.fastcampus.springrunner.divelog.web.diveresort.dto;

import javax.validation.constraints.NotEmpty;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortRegisterCommand;

import lombok.Getter;
import lombok.Setter;

/**
 * 다이브리조트 등록요청
 * 
 * @author springrunner.kr@gmail.com
 *
 */
@Getter
@Setter
public class DiveResortRegisterRequest {
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

    public DiveResortRegisterCommand convertToRegisterCommand() {
        return DiveResortRegisterCommand.create(getName(), getOwnerName(), getContactNumber(), getAddress(),
                getDescription());
    }
}

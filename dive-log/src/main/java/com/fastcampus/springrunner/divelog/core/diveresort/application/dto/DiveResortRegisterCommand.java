package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

import lombok.Getter;

@Getter
public class DiveResortRegisterCommand {
    private String name;
    private String location;
    private String ownerName;
    private String contactNumber;

    public static DiveResortRegisterCommand create(
            String name, 
            String location, 
            String ownerName,
            String contactNumber) {

        DiveResortRegisterCommand registerCommand = new DiveResortRegisterCommand();
        registerCommand.name = name;
        registerCommand.location=location;
        registerCommand.ownerName = ownerName;
        registerCommand.contactNumber = contactNumber;
        return registerCommand;
    }
    
    public DiveResort convertToEntity() {
        return DiveResort.create(getName(), getLocation(), getOwnerName(), getContactNumber());
    }

}

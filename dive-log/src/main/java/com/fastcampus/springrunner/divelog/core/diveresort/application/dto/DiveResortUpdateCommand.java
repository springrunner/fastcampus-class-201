package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

import lombok.Getter;

@Getter
public class DiveResortUpdateCommand {
    private String name;
    private String ownerName;
    private String contactNumber;
    private String address;
    private String description;
    
    public static DiveResortUpdateCommand create(
            String name,
            String ownerName,
            String contactNumber,
            String address,
            String description) {
        
        DiveResortUpdateCommand updateCommand = new DiveResortUpdateCommand();
        updateCommand.name = name;
        updateCommand.ownerName = ownerName;
        updateCommand.contactNumber = contactNumber;
        updateCommand.address = address;
        updateCommand.description = description;
        return updateCommand;
    }
    
    public DiveResort update(DiveResort diveResort) {
        diveResort.update(getName(), getOwnerName(), getContactNumber(), getAddress(), getDescription());
        return diveResort;
    }
}

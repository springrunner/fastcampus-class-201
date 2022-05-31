package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

import lombok.Getter;

@Getter
public class DiveResortUpdateCommand {
    private String name;
    private String location;
    private String ownerName;
    private String contactNumber;
    
    public static DiveResortUpdateCommand create(
            String name,
            String location,
            String ownerName,
            String contactNumber) {
        
        DiveResortUpdateCommand updateCommand = new DiveResortUpdateCommand();
        updateCommand.name = name;
        updateCommand.location = location;
        updateCommand.ownerName = ownerName;
        updateCommand.contactNumber = contactNumber;
        return updateCommand;
    }
    
    public DiveResort update(DiveResort diveResort) {
        diveResort.update(getName(), getLocation(), getOwnerName(), getContactNumber());
        return diveResort;
    }
}

package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

import lombok.Getter;

@Getter
public class DiveResortRegisterCommand {
    private String name;
    private String ownerName;
    private String contactNumber;
    private String address;
    private String description;

    public static DiveResortRegisterCommand create(String name, String ownerName, String contactNumber, String address,
            String description) {

        DiveResortRegisterCommand registerCommand = new DiveResortRegisterCommand();
        registerCommand.name = name;
        registerCommand.address = address;
        registerCommand.ownerName = ownerName;
        registerCommand.contactNumber = contactNumber;
        registerCommand.description = description;
        return registerCommand;
    }

    public DiveResort convertToEntity() {
        return DiveResort.create(getName(), getOwnerName(), getContactNumber(), getAddress(), getDescription());
    }

}

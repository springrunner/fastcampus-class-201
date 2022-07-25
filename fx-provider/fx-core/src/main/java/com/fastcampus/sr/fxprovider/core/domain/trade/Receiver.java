package com.fastcampus.sr.fxprovider.core.domain.trade;

import lombok.Getter;

@Getter
public class Receiver {
    private String name;
    private String email;
    private String contactNumber;
    private String address1;
    private String address2;
    private String identifyNumber;

    public static Receiver of(String name,
                              String email,
                              String contactNumber,
                              String address1,
                              String address2,
                              String identifyNumber){
        var sender = new Receiver();
        sender.name = name;
        sender.email = email;
        sender.contactNumber = contactNumber;
        sender.address1 = address1;
        sender.address2 = address2;
        sender.identifyNumber = identifyNumber;
        return sender;
    }
}

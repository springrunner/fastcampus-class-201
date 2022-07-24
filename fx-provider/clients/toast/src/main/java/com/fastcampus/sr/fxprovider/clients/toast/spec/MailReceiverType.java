package com.fastcampus.sr.fxprovider.clients.toast.spec;

import lombok.Getter;

@Getter
public enum MailReceiverType {
    RECEIVER("받는사람"),
    CC("참조"),
    BCC("숨은참조");

    private final String description;

    MailReceiverType(String description) {
        this.description = description;
    }
}

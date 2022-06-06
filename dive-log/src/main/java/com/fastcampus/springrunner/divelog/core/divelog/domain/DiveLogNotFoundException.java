package com.fastcampus.springrunner.divelog.core.divelog.domain;

@SuppressWarnings("serial")
public class DiveLogNotFoundException extends DiveLogEntityException {
    private Long diveLogId;

    public DiveLogNotFoundException(Long diveLogId) {
        super("DiveLog 엔티티를 찾을 수 없습니다(id: %d)", diveLogId);
        this.diveLogId = diveLogId;
    }

    @Override
    public Object[] getArguments() {
        return new Object[] { String.valueOf(diveLogId) };
    }
}

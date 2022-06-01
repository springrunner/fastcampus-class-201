package com.fastcampus.springrunner.divelog.core.diveresort.domain;

@SuppressWarnings("serial")
public class DiveResortNotFoundException extends DiveResortEntityException {
    private Long diveResortId;

    public DiveResortNotFoundException(Long diveResortId) {
        super("DiveResort 엔티티를 찾을 수 없습니다(id: %d)", diveResortId);
        this.diveResortId = diveResortId;
    }

    @Override
    public Object[] getArguments() {
        return new Object[] { String.valueOf(diveResortId) };
    }
}

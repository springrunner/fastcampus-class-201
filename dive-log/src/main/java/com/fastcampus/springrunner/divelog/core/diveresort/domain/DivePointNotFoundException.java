package com.fastcampus.springrunner.divelog.core.diveresort.domain;

@SuppressWarnings("serial")
public class DivePointNotFoundException extends DivePointEntityException {
    private Long divePointId;

    public DivePointNotFoundException(Long divePointId) {
        super("DivePoint 엔티티를 찾을 수 없습니다(id: %d)", divePointId);
        this.divePointId = divePointId;
    }

    @Override
    public Object[] getArguments() {
        return new Object[] { String.valueOf(divePointId) };
    }
}

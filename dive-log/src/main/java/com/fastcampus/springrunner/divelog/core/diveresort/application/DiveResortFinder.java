package com.fastcampus.springrunner.divelog.core.diveresort.application;

import java.util.List;
import java.util.Optional;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

public interface DiveResortFinder {
    /**
     * 등록된 모든 다이브리조트 목록을 반환한다.
     * 
     * @return List<DiveResort> 개체
     */
    List<DiveResort> findAll();

    /**
     * 요청받은 다이브포인트를 반환한다.
     * 
     * @param diveResortId
     * @return Optional<DiveResort>
     */
    Optional<DiveResort> findByDiveResortId(Long diveResortId);
}

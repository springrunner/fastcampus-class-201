package com.fastcampus.springrunner.divelog.core.divelog.application;

import java.util.List;
import java.util.Optional;

import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLog;

public interface DiveLogFinder {
    /**
     * 등록된 모든 다이브로그 목록을 반환한다.
     * 
     * @return List<DiveLog> 개체
     */
    List<DiveLog> findAll();

    /**
     * 다이브포인트로 등록된 다이브로그 목록을 반환한다.
     * 
     * @param diveResortId
     * @return List<DiveLog> 개체
     */
    List<DiveLog> findByDivePointId(Long divePointId);

    /**
     * 다이브로그를 반환한다.
     * 
     * @param diveLogId
     * @return Optional<DiveLog> 개체
     */
    Optional<DiveLog> findById(Long diveLogId);
}

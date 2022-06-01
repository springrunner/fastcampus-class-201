package com.fastcampus.springrunner.divelog.core.divelog.application;

import java.util.List;
import java.util.Optional;

import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogDto;

public interface DiveLogFinder {
    /**
     * 등록된 모든 다이브로그 목록을 반환한다.
     * 
     * @return List<DiveLogDto> 개체
     */
    List<DiveLogDto> findAll();

    /**
     * 다이브포인트로 등록된 다이브로그 목록을 반환한다.
     * 
     * @param diveResortId
     * @return List<DiveLogDto> 개체
     */
    List<DiveLogDto> findByDivePointId(Long divePointId);

    /**
     * 다이브로그를 반환한다.
     * 
     * @param diveLogId
     * @return Optional<DiveLogDto> 개체
     */
    Optional<DiveLogDto> findById(Long diveLogId);
}

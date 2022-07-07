package com.fastcampus.springrunner.divelog.core.diveresort.application;

import java.util.List;
import java.util.Optional;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointDto;

/**
 * 다이브포인트 검색기 인터페이스
 * @author springrunner.kr@gmail.com
 *
 */
public interface DivePointFinder {
    /**
     * 등록된 모든 다이브포인트 목록을 반환한다.
     * @return List<DivePointDto> 개체
     */
    List<DivePointDto> findAll();
    
    /**
     * 다이브리조트에 등록된 모든 다이브포인트 목록을 반환한다.
     * @param diveResortId 다이브리조트 ID
     * @return List<DivePoint> 개체
     */
    List<DivePointDto> findByDiveResortId(Long diveResortId);
    
    /**
     * 요청받은 다이브포인트를 반환한다.
     * 
     * @param divePointId
     * @return Optional<DivePoint>
     */
    Optional<DivePointDto> findByDivePointId(Long divePointId);
}

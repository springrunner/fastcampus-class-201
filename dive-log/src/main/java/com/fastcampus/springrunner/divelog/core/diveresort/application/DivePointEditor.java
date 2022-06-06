package com.fastcampus.springrunner.divelog.core.diveresort.application;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointDto;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointRegisterCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DivePointUpdateCommand;

/**
 * 다이브포인트 편집기 인터페이스
 * 
 * @author springrunner.kr@gmail.com
 *
 */
public interface DivePointEditor {
    /**
     * 다이브포인트 생성
     * 
     * @param registerCommand 등록명령
     * @return DivePointDto 개체
     */
    DivePointDto save(DivePointRegisterCommand registerCommand);

    /**
     * 다이브포인트 갱신
     * 
     * @param divePointId   다이브포인트ID
     * @param updateCommand 갱신 명령
     * @return DivePointDto 개체
     */
    DivePointDto update(Long divePointId, DivePointUpdateCommand updateCommand);

    /**
     * 다이브포인트 삭제
     * 
     * @param divePointId 다이브포인트 ID
     */
    void delete(Long divePointId);
}

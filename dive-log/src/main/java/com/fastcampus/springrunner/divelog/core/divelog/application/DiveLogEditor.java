package com.fastcampus.springrunner.divelog.core.divelog.application;

import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogDto;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogRegisterCommand;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogUpdateCommand;

public interface DiveLogEditor {
    /**
     * 다이브로그 저장
     * 
     * @param registerCommand
     * @return 등록된 다이브로그 개체
     */
    DiveLogDto save(DiveLogRegisterCommand registerCommand);

    /**
     * 다이브로그 갱신
     * 
     * @param diveLogId     대상 다이브로그ID
     * @param updateCommand 갱신명령
     * @return 갱신된 DiveLogDto 개체
     */
    DiveLogDto update(Long diveLogId, DiveLogUpdateCommand updateCommand);

    /**
     * 다이브로그 삭제
     * 
     * @param diveLogId 삭제대상 diveLogId
     */
    void delete(Long diveLogId);
}

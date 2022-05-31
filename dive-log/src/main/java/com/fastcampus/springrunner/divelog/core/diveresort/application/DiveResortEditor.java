package com.fastcampus.springrunner.divelog.core.diveresort.application;

import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortDto;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortRegisterCommand;
import com.fastcampus.springrunner.divelog.core.diveresort.application.dto.DiveResortUpdateCommand;

public interface DiveResortEditor {

    /**
     * 다이브리조트 등록
     * 
     * @param registCommand
     * @return 등록된 DiveResortDto 개체
     */
    DiveResortDto save(DiveResortRegisterCommand registCommand);

    /**
     * 다이브리조트 변경
     * 
     * @param updateCommand
     * @return 변경된 DiveResortDto 개체
     */
    DiveResortDto update(DiveResortUpdateCommand updateCommand);

    /**
     * 다이브로지트 삭제
     * 
     * @param diveResortId
     */
    void delete(Long diveResortId);
}

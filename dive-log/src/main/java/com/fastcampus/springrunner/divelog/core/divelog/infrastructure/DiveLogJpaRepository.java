package com.fastcampus.springrunner.divelog.core.divelog.infrastructure;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLog;
import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLogRepository;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

public interface DiveLogJpaRepository extends DiveLogRepository, JpaRepository<DiveLog, Long> {
    @Override
    @Query("SELECT diveLog FROM DiveLog diveLog "
            + "JOIN diveLog.divePoint divePoint "
            + "JOIN divePoint.diveResort "
            + "WHERE diveResort = :diveResort")
    List<DiveLog> findByDiveResort(DiveResort diveResort);
}

package com.fastcampus.springrunner.divelog.core.divelog.domain;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

public interface DiveLogRepository {
    /**
     * 모든 다이브로그를 반환한다.
     * <p>
     * 등록된 다이브로그가 없으면 빈 목록을 반환한다.
     *
     * @return List<DiveLog> 개체
     */
    List<DiveLog> findAll();

    /**
     * 다이브리조트로 등록된 모든 다이브로그를 반환한다.
     * <p>
     * 등록된 다이브로그가 없으면 빈 목록을 반환한다.
     *
     * @param diveResort 다이브리조트 개체
     * @return List<DiveLog> 개체
     */
    List<DiveLog> findByDiveResort(DiveResort diveResort);

    /**
     * 다이브포인트로 등록된 모든 다이브로그를 반환한다.
     * <p>
     * 등록된 다이브로그가 없으면 빈 목록을 반환한다.
     *
     * @param divePoint 다이브포인트 개체
     * @return List<DiveLog> 개체
     */
    List<DiveLog> findByDivePoint(DivePoint divePoint);

    /**
     * 다이빙날자로 등록된 모든 다이브로그를 반환한다.
     * <p>
     * 등록된 다이브로그가 없으면 빈 목록을 반환한다.
     *
     * @param diveDate 다이브일
     * @return List<DiveLog> 개체
     */
    List<DiveLog> findByDiveDate(LocalDate diveDate);

    /**
     * 다이브로그 식별번호(ID)로 다이브로그를 찾는다.
     * <p>
     * 일치하는 할 일이 없으면 Optional.empty() 가 반환된다.
     *
     * @param id 다이브로그 식별번호
     * @return Optional<DiveLog> 개체
     */
    Optional<DiveLog> findById(Long id);

    /**
     * 저장소에 다이브로그를 저장한다.
     *
     * @param diveLog 다이브로그 개체
     * @return 저장된 다이브로그 개체
     */
    DiveLog save(DiveLog diveLog);

    /**
     * 저장소에 다이브로그를 삭제한다.
     * <p>
     * 일치하는다이브로그가 없으면 무시한다.
     *
     * @param diveLog 삭제대상 다이브로그
     */
    void delete(DiveLog diveLog);
}

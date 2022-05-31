package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.util.List;
import java.util.Optional;

public interface DivePointRepository {
    /**
     * 모든 다이브포인트 목록을 반환한다.
     * <p>
     * 등록된 다이브포인트가 없으면 빈 목록을 반환한다.
     *
     * @return List<DivePoint> 개체
     */
    List<DivePoint> findAll();

    /**
     * 해당 다이브리조트로 등록된 모든 다이브포인트 목록을 반환한다.
     * <p>
     * 등록된 다이브포인트가 없으면 빈목록을 반환한다.
     *
     * @param diveResort 다이브리조트
     * @return List<DivePoint> 개체
     */
    List<DivePoint> findByDiveResort(DiveResort diveResort);

    /**
     * 해당 다이브포인트명으로 등록된 모든 다이브포인트 목록을 반환한다.
     * <p>
     * 등록된 다이브포인트가 없으면 빈목록을 반환한다.
     *
     * @param divePointName 다이브포인트
     * @return List<DivePoint> 개체
     */
    List<DivePoint> findByName(String divePointName);

    /**
     * 다이브로그 식별번호(id)로 다이브로그를 찾는다.
     * <p>
     * 일치하는 다이브로그가 없으면 {@link Optional#empty()}가 반환된다.
     *
     * @param id 다이브로그 식별번호
     * @return Optional<DivePoint>
     */
    Optional<DivePoint> findById(Long id);

    /**
     * 저장소에 다이브포인트를 저장한다.
     *
     * @param divePoint 다이브포인트 개체
     * @return 저장된 다이브포인트 개체
     */
    DivePoint save(DivePoint divePoint);

    /**
     * 저장소에서 다이브포인트 개체를 제거한다.
     * <p>
     * 일치하는 다이브포인트가 없으면 무시한다.
     *
     * @param divePoint 삭제할 다이브포인트 개체
     */
    void delete(DivePoint divePoint);
}

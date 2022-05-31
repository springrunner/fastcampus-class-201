package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.util.List;
import java.util.Optional;

public interface DiveResortRepository {
    /**
     * 모든 다이브리조트 목록을 반환한다.
     * <p>
     * 등록된 다이브리조트가 없으면 빈 목록을 반환한다.
     *
     * @return List<DiveResort> 개체
     */
    List<DiveResort> findAll();

    /**
     * 해당 다이브리조트이름으로 등록된 모든 다이브리조트 목록을 반환한다.
     * <p>
     * 등록된 다이브리조트가 없으면 빈 목록을 반환한다.
     *
     * @param diveResortName 다이브리조트이름
     * @return List<DiveResort> 개체
     */
    List<DiveResort> findByName(String diveResortName);

    /**
     * 해당 다이브리조트 소유자이름으로 등록된 모든 다이브리조트 목록을 반환한다.
     * <p>
     * 등록된 다이브리조트가 없으면 빈 목록을 반환한다.
     *
     * @param diveResortOwnerName 다이브리조트 소유자이름
     * @return List<DiveResort> 개체
     */
    List<DiveResort> findByOwnerName(String diveResortOwnerName);

    /**
     * 해당 연락처로 등록된 다이브리조트를 찾는다.
     * <p>
     * 일치하는 다이브리조트가 없으면 Optional.empty() 가 반환된다.
     *
     * @param contactNumber 연락처
     * @return Optional<DiveResort> 개체
     */
    Optional<DiveResort> findByContactNumber(String contactNumber);

    /**
     * 다이브리조트 식별번호(id)로 다이브리조트를 찾는다.
     * <p>
     * 일치하는 다이브리조트가 없으면 Optional.empty() 가 반환된다.
     *
     * @param id 다이브리조트 식별번호(id)
     * @return Optional<DiveResort> 개체
     */
    Optional<DiveResort> findById(Long id);

    /**
     * 저장소에 다이브리조트를 저장한다.
     *
     * @param diveResort 다이브로지트 개체
     * @return 저장된 다이브리조트 개체
     */
    DiveResort save(DiveResort diveResort);

    /**
     * 저장소에 다이브리조트를 제거한다.
     * <p>
     * 일치하는 다이브리조트가 없으면 무시한다.
     *
     * @param diveResort 삭제할 다이브리조트 개체
     */
    void delete(DiveResort diveResort);

    void deleteAll();
}

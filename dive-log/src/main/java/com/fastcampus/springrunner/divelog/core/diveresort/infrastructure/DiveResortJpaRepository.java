package com.fastcampus.springrunner.divelog.core.diveresort.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;

public interface DiveResortJpaRepository extends DiveResortRepository, JpaRepository<DiveResort, Long> {

}

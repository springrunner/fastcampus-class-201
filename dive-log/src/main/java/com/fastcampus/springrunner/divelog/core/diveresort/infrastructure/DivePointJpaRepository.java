package com.fastcampus.springrunner.divelog.core.diveresort.infrastructure;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePointRepository;

public interface DivePointJpaRepository extends DivePointRepository, JpaRepository<DivePoint, Long> {

}

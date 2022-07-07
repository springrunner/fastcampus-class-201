package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fastcampus.springrunner.divelog.InMemoryDataJpaTest;

@InMemoryDataJpaTest
class DivePointRepositoryTest {

    @Autowired
    DiveResortRepository diveResortRepository;
    @Autowired
    DivePointRepository divePointRepository;
    
    private DiveResort diveResort;
    
    @BeforeEach
    void setUp() {
        diveResort = DiveResort.create("동해다이브리조트", "허니몬", "82-033-000-0000", "강원도 동해시...", "어디였더라.");
        diveResortRepository.save(diveResort);
    }
    @Test
    void testSave() {
        DivePoint divePoint = DivePoint.create(diveResort, "해뜰무렵", "15-17m", "동해에서 볼 수 있는 전형적인 암반지역으로...");
        
        divePointRepository.save(divePoint);
        
        assertThat(divePoint.getId()).isNotNull();
    }
}

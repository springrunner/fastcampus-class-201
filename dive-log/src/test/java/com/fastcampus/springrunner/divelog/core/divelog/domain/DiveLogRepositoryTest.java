package com.fastcampus.springrunner.divelog.core.divelog.domain;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fastcampus.springrunner.divelog.IntegrationTest;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePointRepository;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;

@IntegrationTest
@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DiveLogRepositoryTest {
    @Autowired
    DiveResortRepository diveResortRepository;
    @Autowired
    DivePointRepository divePointRepository;
    @Autowired
    DiveLogRepository diveLogRepository;
    
    private DiveResort diveResort;
    private List<DiveResort> diveResorts = new ArrayList<>();
    private DivePoint divePoint;
    private List<DivePoint> divePoints = new ArrayList<>();
    private List<DiveLog> diveLogs = new ArrayList<>();
    

    @BeforeEach
    void setUp() {
        diveResort = DiveResort.create("동해다이브", "허니몬", "033-000-000", "강원도 동해시", "TEST");
        diveResortRepository.save(diveResort);
        divePoint = DivePoint.create(diveResort, "Point1", "12~15m", "뿔멍게와 작은 산호들이 잘 이루워진 멋진풍경");
        divePointRepository.save(divePoint);
    }
    
    @AfterEach
    void tearDown() {
        diveLogs.forEach(diveLogRepository::delete);
        divePoints.forEach(divePointRepository::delete);
        diveResorts.forEach(diveResortRepository::delete);
    }
    
    @Test
    void testSave() {
        LocalDate diveDate = LocalDate.now();
        LocalTime entryTime = LocalTime.of(10, 0, 0);
        LocalTime exitTime = LocalTime.of(10, 30, 25);
        String weather = "맑음";
        String buddyName = "뭉글씨";
        String comment = "어제 먹은 술 때문에 숙취가...";
        DiveLog diveLog = DiveLog.create(divePoint, diveDate, entryTime, exitTime, weather, buddyName, comment);
        
        diveLogRepository.save(diveLog);
        
        assertThat(diveLog.getId()).isNotNull();
    }
}

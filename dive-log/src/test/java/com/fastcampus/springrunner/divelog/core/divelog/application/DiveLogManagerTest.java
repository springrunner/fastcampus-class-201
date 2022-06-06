package com.fastcampus.springrunner.divelog.core.divelog.application;

import java.time.LocalDate;
import java.time.LocalTime;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogDto;
import com.fastcampus.springrunner.divelog.core.divelog.application.dto.DiveLogRegisterCommand;
import com.fastcampus.springrunner.divelog.core.divelog.domain.DiveLogRepository;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePointRepository;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;

@SpringBootTest
class DiveLogManagerTest {
    @Autowired
    DiveResortRepository diveResortRepository;
    @Autowired
    DivePointRepository divePointRepository;
    @Autowired
    DiveLogRepository diveLogRepository;
    @Autowired
    DiveLogManager diveLogManager;

    DiveResort diveResort;
    DivePoint divePoint;

    @BeforeEach
    void setUp() {
        diveResort = DiveResort.create("동해다이브", "허니몬", "033-0000-0000", "강원도 동해시", "아직 개업하지 않음");
        diveResortRepository.save(diveResort);

        divePoint = DivePoint.create(diveResort, "Point1: 동서남북", "15-18m", "자연암반지형으로 동쪽에는 정치망이 있으니 조심...");
        divePointRepository.save(divePoint);
    }

    @AfterEach
    void tearDown() {
        diveLogRepository.deleteAll();
        divePointRepository.deleteAll();
        diveResortRepository.deleteAll();
    }

    @Test
    void testSave() {
        LocalDate diveDate = LocalDate.now();
        LocalTime exitTime = LocalTime.now();
        LocalTime entryTime = exitTime.minusMinutes(20);
        String weather = "날씨맑음";
        String buddyName = "손석규";
        String comment = "파도가 거칠었음";

        DiveLogRegisterCommand registerCommand = DiveLogRegisterCommand.create(divePoint.getId(), diveDate, entryTime,
                exitTime, weather, buddyName, comment);

        DiveLogDto diveLogDto = diveLogManager.save(registerCommand);

        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(diveLogDto.getId()).isNotNull();
            softly.assertThat(diveLogDto.getDiveResortId()).isEqualTo(diveResort.getId());
            softly.assertThat(diveLogDto.getDiveResortName()).isEqualTo(diveResort.getName());
            softly.assertThat(diveLogDto.getDivePointId()).isEqualTo(divePoint.getId());
            softly.assertThat(diveLogDto.getDivePointName()).isEqualTo(divePoint.getName());
            softly.assertThat(diveLogDto.getDiveDate()).isEqualTo(diveDate);
            softly.assertThat(diveLogDto.getEntryTime()).isEqualTo(entryTime);
            softly.assertThat(diveLogDto.getExitTime()).isEqualTo(exitTime);
            softly.assertThat(diveLogDto.getWeather()).isEqualTo(weather);
            softly.assertThat(diveLogDto.getBuddyName()).isEqualTo(buddyName);
            softly.assertThat(diveLogDto.getComment()).isEqualTo(comment);
            softly.assertThat(diveLogDto.getCreatedDateTime()).isNotNull();
            softly.assertThat(diveLogDto.getLastModifiedDateTime()).isEqualTo(diveLogDto.getCreatedDateTime());
        });
    }

}

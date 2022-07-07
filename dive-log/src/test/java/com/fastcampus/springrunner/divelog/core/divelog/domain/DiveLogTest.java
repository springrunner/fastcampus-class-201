package com.fastcampus.springrunner.divelog.core.divelog.domain;

import java.time.LocalDate;
import java.time.LocalTime;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;

class DiveLogTest {
    @Test
    void testCreate() {
        DiveResort diveResort = DiveResort.create("동해다이브", "허니몬", "033-000-000", "강원도 동해시", "TEST");
        DivePoint divePoint = DivePoint.create(diveResort, "Point1", "12~15m", "뿔멍게와 작은 산호들이 잘 이루워진 멋진풍경");

        LocalDate diveDate = LocalDate.now();
        LocalTime entryTime = LocalTime.of(10, 0, 0);
        LocalTime exitTime = LocalTime.of(10, 30, 25);
        String weather = "맑음";
        String buddyName = "뭉글씨";
        String comment = "어제 먹은 술 때문에 숙취가...";
        DiveLog diveLog = DiveLog.create(divePoint, diveDate, entryTime, exitTime,
                weather, buddyName, comment);
        
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(diveLog.getDivePoint()).isEqualTo(divePoint);
            softly.assertThat(diveLog.getDiveDate()).isEqualTo(diveDate);
            softly.assertThat(diveLog.getEntryTime()).isEqualTo(entryTime);
            softly.assertThat(diveLog.getExitTime()).isEqualTo(exitTime);
            softly.assertThat(diveLog.getWeather()).isEqualTo(weather);
            softly.assertThat(diveLog.getBuddyName()).isEqualTo(buddyName);
            softly.assertThat(diveLog.getComment()).isEqualTo(comment);
            softly.assertThat(diveLog.getCreatedDateTime()).isNotNull();
            softly.assertThat(diveLog.getLastModifiedDateTime()).isEqualTo(diveLog.getCreatedDateTime());
        });
    }
}
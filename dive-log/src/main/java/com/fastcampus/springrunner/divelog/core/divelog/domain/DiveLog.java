package com.fastcampus.springrunner.divelog.core.divelog.domain;

import com.fastcampus.springrunner.divelog.core.common.AbstractEntity;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DivePoint;

import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Objects;

import javax.persistence.*;

import org.springframework.util.Assert;

@Getter
@Entity
public class DiveLog extends AbstractEntity {
    @JoinColumn(name = "dive_point_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DivePoint divePoint;
    private LocalDate diveDate;
    private LocalTime entryTime;
    private LocalTime exitTime;
    private String weather;
    private String buddyName;
    private String comment;

    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    private static void validateDiveLogArguments(LocalDate diveDate, LocalTime entryTime, LocalTime exitTime,
            String weather, String buddyName, String comment) {
        Assert.notNull(diveDate, "diveDate 는 필수입력값입니다.");
        Assert.notNull(entryTime, "entryTime 은 필수입력값입니다.");
        Assert.notNull(exitTime, "exitTime 은 필수입력값입니다.");
        Assert.hasText(weather, "weather 은 필수입력값입니다.");
        Assert.hasText(buddyName, "buddyName 는 필수입력값입니다.");
        Assert.hasText(comment, "comment 는 필수입력값입니다.");
    }

    /**
     * DiveLog 생성
     * 
     * @param divePoint 다이브포인트
     * @param diveDate  다이브일자
     * @param entryTime 입수시
     * @param exitTime  출수시
     * @param weather   날씨
     * @param buddyName 버디
     * @param comment   코멘트
     * @return 새로운 DiveLog 개체
     */
    public static DiveLog create(DivePoint divePoint, LocalDate diveDate, LocalTime entryTime, LocalTime exitTime,
            String weather, String buddyName, String comment) {
        validateDiveLogArguments(diveDate, entryTime, exitTime, weather, buddyName, comment);

        DiveLog diveLog = new DiveLog();
        diveLog.divePoint = divePoint;
        diveLog.diveDate = diveDate;
        diveLog.entryTime = entryTime;
        diveLog.exitTime = exitTime;
        diveLog.weather = weather;
        diveLog.buddyName = buddyName;
        diveLog.comment = comment;
        diveLog.createdDateTime = LocalDateTime.now();
        diveLog.lastModifiedDateTime = diveLog.getCreatedDateTime();
        return diveLog;
    }

    /**
     * 다이브로그 업데이트
     * 
     * @param diveDate  다이브일자
     * @param entryTime 입수시
     * @param exitTime  출수시
     * @param weather   날씨
     * @param buddyName 버디
     * @param comment   코멘트
     */
    public void update(LocalDate diveDate, LocalTime entryTime, LocalTime exitTime, String weather, String buddyName,
            String comment) {
        validateDiveLogArguments(diveDate, entryTime, exitTime, weather, buddyName, comment);

        this.diveDate = diveDate;
        this.entryTime = entryTime;
        this.exitTime = exitTime;
        this.weather = weather;
        this.buddyName = buddyName;
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        return Objects.hash(diveDate, divePoint, entryTime, exitTime);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiveLog other = (DiveLog) obj;
        return Objects.equals(diveDate, other.diveDate) && Objects.equals(divePoint, other.divePoint)
                && Objects.equals(entryTime, other.entryTime) && Objects.equals(exitTime, other.exitTime);
    }
}

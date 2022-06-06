package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
@Entity
public class DivePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @JoinColumn(name = "dive_resort_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private DiveResort diveResort;
    private String name;
    private String depth;
    private String description;

    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    private static void validateDivePointArguments(String name, String depth, String description) {
        Assert.hasText(name, "name 은 필수입력값입니다.");
        Assert.hasText(depth, "depth 는 필수입력값입니다.");
        Assert.hasText(description, "description 은 필수입력값입니다.");
    }

    public static DivePoint create(DiveResort diveResort, String name, String depth, String description) {
        Assert.notNull(diveResort, "diveResort 는 필수입력값입니다.");
        validateDivePointArguments(name, depth, description);

        DivePoint divePoint = new DivePoint();
        divePoint.diveResort = diveResort;
        divePoint.name = name;
        divePoint.depth = depth;
        divePoint.description = description;
        divePoint.createdDateTime = LocalDateTime.now();
        divePoint.lastModifiedDateTime = divePoint.createdDateTime;

        return divePoint;
    }

    public void update(String name, String depth, String description) {
        validateDivePointArguments(name, depth, description);
        
        this.name = name;
        this.depth = depth;
        this.description = description;
    }
}

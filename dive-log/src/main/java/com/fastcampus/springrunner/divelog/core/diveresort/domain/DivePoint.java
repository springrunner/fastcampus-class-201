package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

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

    public static DivePoint create(DiveResort diveResort, String name, String depth, String description) {

        DivePoint divePoint = new DivePoint();
        divePoint.diveResort = diveResort;
        divePoint.name = name;
        divePoint.depth = depth;
        divePoint.description = description;
        divePoint.createdDateTime = LocalDateTime.now();
        divePoint.lastModifiedDateTime = divePoint.createdDateTime;

        return divePoint;
    }
}

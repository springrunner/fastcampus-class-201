package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class DivePointTest {
    String diveResortName = "동해다이브리조트";
    String diveResortOwnerName = "허니몬";
    String diveResortContactNumber = "033-0000-0000";
    String diveResortAddress = "강원도 동해시 감추...";
    String diveResortDescription = "동해 어느구석";
    DiveResort diveResort;
    
    @BeforeEach
    void setUp() {
        diveResort = DiveResort.create(diveResortName, diveResortOwnerName, diveResortContactNumber, diveResortAddress, diveResortDescription);
    }
    
    
    @Test
    void testCreate() {
        
        String pointName = "허니몬즈우드";
        String pointDepth = "15~18미터";
        String pointDescription = "모래밭위에 직육면체형 어초를 쌓아 만든 포인트로 봄에는...";
        
        DivePoint divePoint = DivePoint.create(diveResort, pointName, pointDepth, pointDescription);
        
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(divePoint.getDiveResort()).isEqualTo(diveResort);
            softly.assertThat(divePoint.getName()).isEqualTo(pointName);
            softly.assertThat(divePoint.getDepth()).isEqualTo(pointDepth);
            softly.assertThat(divePoint.getDescription()).isEqualTo(pointDescription);
        });
    }
}

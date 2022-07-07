package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import org.assertj.core.api.Assertions;
import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class DiveResortTest {

    @Test
    void testCreate() {
        // given: 준비
        String name = "동해다이브리조트";
        String ownerName = "허니몬";
        String contactNumber = "033-0000-0000";
        String address = "강원도 동해시 감추...";
        String description = "동해 어느구석";

        // when: 실행
        DiveResort diveResort = DiveResort.create(name, ownerName, contactNumber, address, description);

        // then: 검증
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(diveResort.getName()).isEqualTo(name);
            softly.assertThat(diveResort.getOwnerName()).isEqualTo(ownerName);
            softly.assertThat(diveResort.getContactNumber()).isEqualTo(contactNumber);
            softly.assertThat(diveResort.getAddress()).isEqualTo(address);
            softly.assertThat(diveResort.getDescription()).isEqualTo(description);
            softly.assertThat(diveResort.getCreatedDateTime()).isNotNull();
            softly.assertThat(diveResort.getLastModifiedDateTime()).isNotNull();
            softly.assertThat(diveResort.getLastModifiedDateTime()).isEqualTo(diveResort.getCreatedDateTime());
        });
    }

    @ParameterizedTest
    @CsvSource(value = { 
            ",허니몬,033-0000-0000,동해시,동해어느구석", 
            "동해다이브리조트,,033-0000-0000,동해시,동해어느구석",
            "동해다이브리조트,허니몬,,동해시,동해어느구석", 
            "동해다이브리조트,허니몬,033-0000-0000,,동해어느구석", 
            "동해다이브리조트,허니몬,033-0000-0000,동해시," })
    void testValidateArgs(String name, String ownerName, String contactNumber, String address, String description)
            throws Exception {
        Assertions.assertThatThrownBy(() -> DiveResort.create(name, ownerName, contactNumber, address, description))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testUpdate() throws Exception {
        // given: 준비
        String name = "동해다이브리조트";
        String ownerName = "허니몬";
        String contactNumber = "033-0000-0000";
        String address = "강원도 동해시 감추...";
        String description = "동해 어느구석";

        DiveResort diveResort = DiveResort.create(name, ownerName, contactNumber, address, description);

        String updatedName = "동해다이브리조트!";
        String updatedOwnerName = "허니몬!";
        String updatedContactNumber = "033-0000-0000!";
        String updatedAddress = "강원도 동해시 감추...!";
        String updatedDescription = "동해 어느구석!";

        // when: 실행
        diveResort.update(updatedName, updatedOwnerName, updatedContactNumber, updatedAddress, updatedDescription);

        // then: 검증
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(diveResort.getName()).isNotEqualTo(name);
            softly.assertThat(diveResort.getOwnerName()).isNotEqualTo(ownerName);
            softly.assertThat(diveResort.getContactNumber()).isNotEqualTo(contactNumber);
            softly.assertThat(diveResort.getAddress()).isNotEqualTo(address);
            softly.assertThat(diveResort.getDescription()).isNotEqualTo(description);

            softly.assertThat(diveResort.getName()).isEqualTo(updatedName);
            softly.assertThat(diveResort.getOwnerName()).isEqualTo(updatedOwnerName);
            softly.assertThat(diveResort.getContactNumber()).isEqualTo(updatedContactNumber);
            softly.assertThat(diveResort.getAddress()).isEqualTo(updatedAddress);
            softly.assertThat(diveResort.getDescription()).isEqualTo(updatedDescription);
            softly.assertThat(diveResort.getCreatedDateTime()).isNotNull();
            softly.assertThat(diveResort.getLastModifiedDateTime()).isNotNull();
        });
    }
}

package com.fastcampus.springrunner.divelog.core.diveresort.application.dto;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.Test;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.web.diveresort.dto.DiveResortRegisterRequest;

class DiveResortRegisterCommandTest {

    @Test
    void test() {
        // given
        String name = "동해다이브리조트";
        String ownerName = "허니몬";
        String contactNumber = "033-0000-0000";
        String address = "강원도 동해시 감추...";
        String description = "동해 어느구석";
        DiveResortRegisterRequest registerRequest = new DiveResortRegisterRequest();
        registerRequest.setName(name);
        registerRequest.setOwnerName(ownerName);
        registerRequest.setContactNumber(contactNumber);
        registerRequest.setAddress(address);
        registerRequest.setDescription(description);

        DiveResortRegisterCommand registerCommand = registerRequest.convertToRegisterCommand();

        // when
        DiveResort diveResort = registerCommand.convertToEntity();

        // then
        SoftAssertions.assertSoftly(softly -> {
            softly.assertThat(diveResort.getName()).isEqualTo(name);
            softly.assertThat(diveResort.getOwnerName()).isEqualTo(ownerName);
            softly.assertThat(diveResort.getContactNumber()).isEqualTo(contactNumber);
            softly.assertThat(diveResort.getAddress()).isEqualTo(address);
            softly.assertThat(diveResort.getDescription()).isEqualTo(description);
        });
    }

}

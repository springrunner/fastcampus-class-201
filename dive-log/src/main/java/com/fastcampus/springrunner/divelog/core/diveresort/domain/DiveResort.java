package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;

import org.springframework.util.Assert;

import com.fastcampus.springrunner.divelog.core.common.AbstractEntity;

import lombok.Getter;

@Getter
@Entity
public class DiveResort extends AbstractEntity{
    private String name; // 리조트
    private String ownerName; // 리조트사장님이
    private String contactNumber; // 리조트연락처
    private String address; // 리조트 주소
    private String description; // 리조트 설명
    private LocalDateTime createdDateTime; // 생성일시
    private LocalDateTime lastModifiedDateTime; // 최근변경일시

    private static void validateDiveResortArguments(String name, String ownerName, String contactNumber,
            String address, String description) {
        Assert.hasText(name, "name 은 필수입력값입니다.");
        Assert.hasText(ownerName, "ownerName 은 필수입력값입니다.");
        Assert.hasText(contactNumber, "contactNumber 은 필수입력값입니다.");
        Assert.hasText(address, "address 은 필수입력값입니다.");
        Assert.hasText(description, "description 은 필수입력값입니다.");
    }

    public static DiveResort create(String name, String ownerName, String contactNumber, String address,
            String description) {
        validateDiveResortArguments(name, ownerName, contactNumber, address, description);

        DiveResort diveResort = new DiveResort();
        diveResort.name = name;
        diveResort.ownerName = ownerName;
        diveResort.contactNumber = contactNumber;
        diveResort.address = address;
        diveResort.description = description;
        diveResort.createdDateTime = LocalDateTime.now();
        diveResort.lastModifiedDateTime = diveResort.getCreatedDateTime();

        return diveResort;
    }

    public void update(String name, String ownerName, String contactNumber, String address, String description) {
        validateDiveResortArguments(name, ownerName, contactNumber, address, description);
        
        this.name = name;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
        this.address = address;
        this.description = description;
        this.lastModifiedDateTime = LocalDateTime.now();
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = super.hashCode();
        result = prime * result + Objects.hash(address, contactNumber, createdDateTime, description,
                lastModifiedDateTime, name, ownerName);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!super.equals(obj))
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiveResort other = (DiveResort) obj;
        return Objects.equals(address, other.address) && Objects.equals(contactNumber, other.contactNumber)
                && Objects.equals(createdDateTime, other.createdDateTime)
                && Objects.equals(description, other.description)
                && Objects.equals(lastModifiedDateTime, other.lastModifiedDateTime) && Objects.equals(name, other.name)
                && Objects.equals(ownerName, other.ownerName);
    }
}

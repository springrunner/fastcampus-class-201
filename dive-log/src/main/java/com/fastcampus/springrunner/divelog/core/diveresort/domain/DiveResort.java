package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.time.LocalDateTime;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
@Entity
public class DiveResort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name; // 리조트
    private String ownerName; // 리조트사장님이
    private String contactNumber; // 리조트연락처
    private String address; // 리조트 주소
    private String description; // 리조트 설명
    private LocalDateTime createdDateTime; // 생성일시
    private LocalDateTime lastModifiedDateTime; // 최근변경일시

    @Override
    public int hashCode() {
        return Objects.hash(contactNumber, id, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        DiveResort other = (DiveResort) obj;
        return Objects.equals(contactNumber, other.contactNumber) && Objects.equals(id, other.id)
                && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "DiveResort [id=" + id + ", name=" + name + ", ownerName=" + ownerName + ", contactNumber="
                + contactNumber + ", address=" + address + ", description=" + description + ", createdDateTime="
                + createdDateTime + ", lastModifiedDateTime=" + lastModifiedDateTime + "]";
    }

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
}

package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import java.time.LocalDateTime;

import javax.persistence.*;

import org.springframework.util.Assert;

import lombok.Getter;

@Getter
@Entity
public class DiveResort {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column 
    private String name; // String type 컬럼의 크기를 별도로 설정하지 않는다면 varchar(255)로 설정된다.
    @Column
    private String location;
    @Column
    private String ownerName;
    @Column
    private String contactNumber;
    
//    @OneToMany(mappedBy = "diveResort", fetch = FetchType.LAZY)
//    private List<DivePoint> divePoints = new ArrayList<>();

    private LocalDateTime createdDateTime;
    private LocalDateTime lastModifiedDateTime;

    private static void validateInputSource(String name, String location, String ownerName, String contactNumber) {
        Assert.hasText(name, "name 은 필수입력값입니다.");
        Assert.hasText(location, "location 은 필수입력값입니다.");
        Assert.hasText(ownerName, "ownerNumber 은 필수입력값입니다.");
        Assert.hasText(contactNumber, "contactNUmber 은 필수입력값입니다.");
    }

    public static DiveResort create(String name, String location, String ownerName, String contactNumber) {
        validateInputSource(name, location, ownerName, contactNumber);

        DiveResort diveResort = new DiveResort();
        diveResort.name = name;
        diveResort.location = location;
        diveResort.ownerName = ownerName;
        diveResort.contactNumber = contactNumber;
        diveResort.createdDateTime = LocalDateTime.now();
        diveResort.lastModifiedDateTime = diveResort.createdDateTime;
        return diveResort;
    }

    public void update(String name, String location, String ownerName, String contactNumber) {
        validateInputSource(name, location, ownerName, contactNumber);

        this.name = name;
        this.location = location;
        this.ownerName = ownerName;
        this.contactNumber = contactNumber;
        this.lastModifiedDateTime = LocalDateTime.now();
    }
}

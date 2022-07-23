package com.fastcampus.sr.fxprovider.core;

import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.Date;

@Getter
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "created_at", columnDefinition = "TIMESTAMP COMMENT '생성일시'")
    private ZonedDateTime createdAt;

    @Column(name = "modified_at", columnDefinition = "TIMESTAMP COMMENT '변경일시'")
    private ZonedDateTime modifiedAt;

    @PrePersist
    public void prePersist() {
        this.createdAt = ZonedDateTime.now();
        this.modifiedAt = ZonedDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.modifiedAt = ZonedDateTime.now();
    }
}

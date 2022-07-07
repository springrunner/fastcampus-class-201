package com.fastcampus.springrunner.divelog.core.diveresort.application;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;

@ExtendWith(MockitoExtension.class)
class DiveResortManagerTest {
    @Mock
    private DiveResortRepository diveResortRepository;
    private DiveResortManager diveResortManager;
    
    @BeforeEach
    void setUp() {
        diveResortManager = new DiveResortManager(diveResortRepository);
    }

    @Test
    void test() {
        diveResortManager.findAll();
    }

}

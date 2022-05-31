package com.fastcampus.springrunner.divelog.core.diveresort.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.fastcampus.springrunner.divelog.InMemoryDataJpaTest;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResort;
import com.fastcampus.springrunner.divelog.core.diveresort.domain.DiveResortRepository;

//@DataJpaTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@InMemoryDataJpaTest
class DiveResortJpaRepositoryTest {
    @Autowired
    DiveResortRepository diveResortRepository;

    @Test
    void test() {
        DiveResort diveResort = DiveResort.create("Test", "강원도 동해시", "Tester", "82-010-0000-0000");
        
        diveResortRepository.save(diveResort);
        
        assertThat(diveResort.getId()).isNotNull();
    }
}

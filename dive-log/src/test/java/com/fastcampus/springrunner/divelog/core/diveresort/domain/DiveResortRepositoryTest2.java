package com.fastcampus.springrunner.divelog.core.diveresort.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.fastcampus.springrunner.divelog.InMemoryDataJpaTest;

/**
 * h2database 1.4.200 에서 DATABASE_TO_UPPER=true 기본설정되어있고, @DataJpaTest 는 해당 옵션을
 * 변경하지 않음 그래서 @DataJpaTest 를 사용하는 경우
 * <p/>
 * <code>@AutoConfigureTestDatabase(replace = Replace.NONE)</code> 도 함께 선언해야 함.
 * @see 그게 싫은 경우 {@link InMemoryDataJpaTest 컴포지드 애노테이션을 만들어서 사용가능함
 *
 * @author springrunner.kr@gmail.com
 *
 */
@DataJpaTest(properties = {"spring.mvc.format.date=yyyy/MM/dd"})
@AutoConfigureTestDatabase(replace = Replace.NONE)
class DiveResortRepositoryTest2 {
    @Autowired
    DiveResortRepository repository;

    @Test
    void testSave() {
        DiveResort diveResort = DiveResort.create("동해다이브", "허니몬", "82-033-0000-000", "강원도 동해시...", "어딘가!!");

        repository.save(diveResort);

        assertThat(diveResort.getId()).isNotNull();
    }

}

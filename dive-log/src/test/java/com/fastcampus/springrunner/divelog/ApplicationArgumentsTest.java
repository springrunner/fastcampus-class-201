package com.fastcampus.springrunner.divelog;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(args = {"--app.name=dive-log-test"})
public class ApplicationArgumentsTest {
    @Test
    void testApplicationArguments(@Autowired ApplicationArguments appArguments) {
        assertThat(appArguments.getOptionNames()).containsOnly("app.name");
        assertThat(appArguments.getOptionValues("app.name")).containsOnly("dive-log-test");
    }

}

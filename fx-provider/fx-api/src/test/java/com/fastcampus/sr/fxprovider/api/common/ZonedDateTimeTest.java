package com.fastcampus.sr.fxprovider.api.common;

import org.junit.jupiter.api.Test;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static org.assertj.core.api.Assertions.assertThat;

public class ZonedDateTimeTest {
    @Test
    void testZonedDateTimeNow() {
        ZonedDateTime source = ZonedDateTime.now();

        assertThat(source.getZone()).isEqualTo(ZoneId.systemDefault());
        assertThat(source.getZone()).isEqualTo(ZoneId.of("Asia/Seoul"));
        System.out.println(source);
    }

    @Test
    void testParse() {
        String source = "2022-07-21T23:59:59+09:00[Asia/Seoul]";

        ZonedDateTime dateTime = ZonedDateTime.parse(source, DateTimeFormatter.ISO_ZONED_DATE_TIME);

        System.out.println(dateTime);
    }
}

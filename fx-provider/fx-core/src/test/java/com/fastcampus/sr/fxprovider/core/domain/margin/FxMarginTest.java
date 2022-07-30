package com.fastcampus.sr.fxprovider.core.domain.margin;

import com.fastcampus.sr.fxprovider.common.enums.MarginType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.math.BigDecimal;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class FxMarginTest {

    FxMargin fxMargin = new FxMargin(BigDecimal.ONE, BigDecimal.valueOf(100), MarginType.FIX, BigDecimal.valueOf(10));

    @ParameterizedTest
    @MethodSource("caseIsTarget")
    void testIsTarget(BigDecimal source, boolean isTarget) {
        assertThat(fxMargin.isTarget(source)).isEqualTo(isTarget);
    }

    private static Stream<Arguments> caseIsTarget() {
        return Stream.of(
                Arguments.of(BigDecimal.valueOf(0), false),
                Arguments.of(BigDecimal.valueOf(0.999), false),
                Arguments.of(BigDecimal.valueOf(1.00), true),
                Arguments.of(BigDecimal.valueOf(1.01), true),
                Arguments.of(BigDecimal.valueOf(1), true),
                Arguments.of(BigDecimal.valueOf(2), true),
                Arguments.of(BigDecimal.valueOf(99.9), true),
                Arguments.of(BigDecimal.valueOf(99.99), true),
                Arguments.of(BigDecimal.valueOf(100), true),
                Arguments.of(BigDecimal.valueOf(100.0), true),
                Arguments.of(BigDecimal.valueOf(100.1), false),
                Arguments.of(BigDecimal.valueOf(101.1), false)
        );
    }
}
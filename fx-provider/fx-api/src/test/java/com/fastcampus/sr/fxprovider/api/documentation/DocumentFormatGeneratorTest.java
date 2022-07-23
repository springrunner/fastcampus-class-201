package com.fastcampus.sr.fxprovider.api.documentation;

import com.fastcampus.sr.fxprovider.common.type.trade.TradeStatus;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class DocumentFormatGeneratorTest {
    @Test
    void testGeneratedEnums() {
        var result = DocumentFormatGenerator.generatedEnums(TradeStatus.class);

        assertThat(result).isEqualTo("REQUEST\n" +
                "IN_PROGRESS\n" +
                "CANCELED\n" +
                "COMPLETED");
    }

    @Test
    void testGeneratedEnumAttrs() {
        var result = DocumentFormatGenerator.generatedEnumAttrs(TradeStatus.class, TradeStatus::getDescription);

        assertThat(result.getValue()).isEqualTo("REQUEST(요청)\n" +
                "IN_PROGRESS(진행중)\n" +
                "CANCELED(취소완료)\n" +
                "COMPLETED(처리완료)");
    }
}

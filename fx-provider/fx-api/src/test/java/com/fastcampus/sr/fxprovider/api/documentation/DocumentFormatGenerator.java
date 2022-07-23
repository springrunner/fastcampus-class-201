package com.fastcampus.sr.fxprovider.api.documentation;

import org.springframework.restdocs.snippet.Attributes;

import java.util.Arrays;
import java.util.function.Function;
import java.util.stream.Collectors;

public class DocumentFormatGenerator {

    private DocumentFormatGenerator() {
        throw new UnsupportedOperationException("utility class.");
    }

    public static Attributes.Attribute required() {
        return Attributes.key("required").value("true");
    }

    public static Attributes.Attribute optional() {
        return Attributes.key("required").value("false");
    }

    public static Attributes.Attribute customFormat(String format) {
        return Attributes.key("format").value(format);
    }

    public static Attributes.Attribute emptyFormat() {
        return customFormat("");
    }

    public static Attributes.Attribute zonedDateTimeFormat() {
        return Attributes.key("format").value("yyyy-MM-dd'T'HH:mm:ss+09:00[Asia/Seoul]");
    }

    /**
     * Enum 타입 문자열 출력
     * @param enumClass 대상 enum
     * @return 문자열
     * @param <E> enum 클래스
     */
    public static <E extends Enum<E>> String generatedEnums(Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .map(Enum::name)
                .collect(Collectors.joining("\n"));
    }

    public static <E extends Enum<E>> Attributes.Attribute generatedEnumAttrs(Class<E> enumClass, Function<E, String> func) {
        var value = Arrays.stream(enumClass.getEnumConstants())
                .map(el -> el.name() + "(" + func.apply(el) + ")")
                .collect(Collectors.joining("\n"));

        return Attributes.key("format").value(value);
    }
}

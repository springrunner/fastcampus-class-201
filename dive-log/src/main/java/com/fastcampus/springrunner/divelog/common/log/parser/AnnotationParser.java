package com.fastcampus.springrunner.divelog.common.log.parser;

import java.lang.annotation.Annotation;
import java.lang.reflect.Method;

public class AnnotationParser {

    public static <T extends Annotation> T parseAnnotation(Method invokeMethod, Class<T> annotationClass) {
        T annotation = invokeMethod.getAnnotation(annotationClass);
        if (annotation == null) {
            annotation = invokeMethod.getDeclaringClass().getAnnotation(annotationClass);
        }
        return annotation;
    }
}

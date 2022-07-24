package com.fastcampus.sr.fx.provider.core.annotation;

import com.fastcampus.sr.fxprovider.common.Constant;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.restdocs.RestDocumentationExtension;

import java.lang.annotation.*;

/**
 * Spring REST Docs 용 통합 애노테이션
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Tag(Constant.TEST_TAG_DOCS_TEST)
@ExtendWith({MockitoExtension.class, RestDocumentationExtension.class})
@AutoConfigureMockMvc
@AutoConfigureRestDocs
public @interface RestDocsTest {
}

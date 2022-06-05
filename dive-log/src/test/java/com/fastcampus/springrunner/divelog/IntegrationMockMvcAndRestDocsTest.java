package com.fastcampus.springrunner.divelog;

import java.lang.annotation.*;

import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs // RestDocs 문서생성을 위해 필요함
public @interface IntegrationMockMvcAndRestDocsTest {
}

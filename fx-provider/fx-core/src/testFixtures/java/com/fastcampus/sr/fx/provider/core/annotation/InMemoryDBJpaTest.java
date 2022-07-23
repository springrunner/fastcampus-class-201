package com.fastcampus.sr.fx.provider.core.annotation;

import com.fastcampus.sr.fxprovider.core.Constant;
import org.junit.jupiter.api.Tag;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@ActiveProfiles({"db", "db-test", Constant.PROFILE_TEST})
@Tag(Constant.TEST_TAG_INTEGRATION)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public @interface InMemoryDBJpaTest {
}

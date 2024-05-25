package com.thewhite.blank;

import com.jupiter.tools.spring.test.postgres.annotation.meta.EnablePostgresIntegrationTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@EnablePostgresIntegrationTest
class AppIT {

    @Test
    @DisplayName("Интеграционный тест подъема контекста")
    void contextRunTest() {}

}

package ru.intetech.weatherTest.configuration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertNotNull;

/**
 * Unit-тесты для класса {@link RestTemplateConfig}.
 * <p>
 * Проверяет, что бин {@link RestTemplate} корректно создается и настраивается.
 * </p>
 */
@SpringBootTest
public class RestTemplateConfigTest {

    @Autowired
    private RestTemplate restTemplate;

    /**
     * Проверяет, что бин {@link RestTemplate} создается и внедряется корректно.
     */
    @Test
    public void shouldCreateRestTemplateBean() {
        assertNotNull(restTemplate, "Бин RestTemplate должен быть создан и внедрен");
    }
}

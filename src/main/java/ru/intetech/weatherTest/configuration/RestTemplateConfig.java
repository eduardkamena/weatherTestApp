package ru.intetech.weatherTest.configuration;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Конфигурационный класс для настройки и создания бина {@link RestTemplate}.
 * <p>
 * Этот класс предоставляет бин {@link RestTemplate}, который используется для выполнения HTTP-запросов
 * к внешним API. Например, для получения данных о погоде с OpenWeatherMap.
 * </p>
 */
@Configuration
public class RestTemplateConfig {

    /**
     * Создает и возвращает бин {@link RestTemplate}.
     * <p>
     * Этот метод использует {@link RestTemplateBuilder} для создания и настройки экземпляра {@link RestTemplate}.
     * {@link RestTemplate} — это мощный инструмент для выполнения HTTP-запросов в Spring-приложениях.
     * </p>
     *
     * @return экземпляр {@link RestTemplate}, настроенный с помощью {@link RestTemplateBuilder}.
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplateBuilder().build();
    }
}

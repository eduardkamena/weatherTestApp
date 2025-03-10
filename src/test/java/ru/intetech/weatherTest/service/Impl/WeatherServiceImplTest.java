package ru.intetech.weatherTest.service.Impl;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import ru.intetech.weatherTest.dto.Weather;
import ru.intetech.weatherTest.dto.WeatherInfo;
import ru.intetech.weatherTest.dto.WeatherMain;
import ru.intetech.weatherTest.entity.WeatherRequestHistory;
import ru.intetech.weatherTest.repository.WeatherRequestRepository;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

/**
 * Unit-тесты для класса {@link WeatherServiceImpl}.
 */
@ExtendWith(MockitoExtension.class)
public class WeatherServiceImplTest {

    @Mock
    private RestTemplate restTemplate;

    @Mock
    private WeatherRequestRepository weatherRequestRepository;

    @InjectMocks
    private WeatherServiceImpl weatherService;

    /**
     * Проверяет, что метод {@link WeatherServiceImpl#getWeather(double, double)} корректно возвращает данные о погоде.
     */
    @Test
    public void shouldGetWeather() {
        // given
        double lat = 55.7558;
        double lon = 37.6176;
        String weatherUrl = "https://api.openweathermap.org/data/2.5/weather?lat={lat}&lon={lon}&units=metric&lang=ru&appid={apikey}";
        String apiKey = "test-api-key";

        WeatherInfo weatherInfo = new WeatherInfo("ясно");
        WeatherMain weatherMain = new WeatherMain(20, 18);
        Weather expectedWeather = new Weather(Collections.singletonList(weatherInfo), weatherMain);

        // when
        when(restTemplate.exchange(
                eq(weatherUrl),
                eq(HttpMethod.GET),
                any(HttpEntity.class),
                eq(Weather.class),
                eq(lat),
                eq(lon),
                eq(apiKey)
        )).thenReturn(ResponseEntity.ok(expectedWeather));

        setField(weatherService, "weatherUrl", weatherUrl);
        setField(weatherService, "weatherApiKey", apiKey);
        Weather result = weatherService.getWeather(lat, lon);

        // then
        assertNotNull(result, "Результат не должен быть null");
        assertEquals(expectedWeather, result, "Должны быть возвращены корректные данные о погоде");
        verify(weatherRequestRepository, times(1)).save(any(WeatherRequestHistory.class));
    }

    /**
     * Вспомогательный метод для установки значений приватных полей через рефлексию.
     */
    private void setField(Object target, String fieldName, Object value) {
        try {
            var field = target.getClass().getDeclaredField(fieldName);
            field.setAccessible(true);
            field.set(target, value);
        } catch (Exception e) {
            throw new RuntimeException("Ошибка при установке поля через рефлексию", e);
        }
    }
}

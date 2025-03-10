package ru.intetech.weatherTest.controller;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import ru.intetech.weatherTest.dto.Weather;
import ru.intetech.weatherTest.dto.WeatherInfo;
import ru.intetech.weatherTest.dto.WeatherMain;
import ru.intetech.weatherTest.service.WeatherService;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

/**
 * Unit-тесты для класса {@link WeatherController}.
 */
@ExtendWith(MockitoExtension.class)
public class WeatherControllerTest {

    @Mock
    private WeatherService weatherService;

    @InjectMocks
    private WeatherController weatherController;

    /**
     * Тест для метода {@link WeatherController#getWeather(double, double)}.
     * Проверяет, что метод возвращает корректные данные о погоде.
     */
    @Test
    public void shouldGetWeather() {
        // given
        double lat = 55.7558;
        double lon = 37.6176;
        List<WeatherInfo> weatherInfo = Collections.singletonList(new WeatherInfo("ясно"));
        WeatherMain weatherMain = new WeatherMain(20, 18);
        Weather expectedWeather = new Weather(weatherInfo, weatherMain);

        // when
        when(weatherService.getWeather(lat, lon)).thenReturn(expectedWeather);
        ResponseEntity<Weather> response = weatherController.getWeather(lat, lon);

        // then
        assertEquals(200, response.getStatusCode().value());
        assertEquals(expectedWeather, response.getBody()); // Проверяем тело ответа
    }

    /**
     * Тест для метода {@link WeatherController#getWeather(double, double)}.
     * Проверяет, что метод корректно обрабатывает случай, когда сервис возвращает null.
     */
    @Test
    public void shouldGetWeatherWhenServiceReturnsNull() {
        // given
        double lat = 55.7558;
        double lon = 37.6176;

        // when
        when(weatherService.getWeather(lat, lon)).thenReturn(null);
        ResponseEntity<Weather> response = weatherController.getWeather(lat, lon);

        // then
        assertEquals(200, response.getStatusCode().value()); // Проверяем статус ответа
        assertNull(response.getBody()); // Проверяем, что тело ответа null
    }
}

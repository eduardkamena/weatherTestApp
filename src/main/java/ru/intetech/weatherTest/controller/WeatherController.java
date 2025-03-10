package ru.intetech.weatherTest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import ru.intetech.weatherTest.dto.Weather;
import ru.intetech.weatherTest.service.WeatherService;

/**
 * Контроллер для работы с данными о погоде.
 * <p>
 * Этот контроллер предоставляет REST API для получения информации о погоде по координатам (широта и долгота).
 * </p>
 */
@RestController
@RequestMapping(path = "/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    /**
     * Возвращает информацию о погоде по указанным координатам.
     * <p>
     * Этот метод обрабатывает GET-запросы по пути {@code /weather} и возвращает данные о погоде
     * в формате JSON. Для получения данных используются широта и долгота, переданные в параметрах запроса.
     * </p>
     *
     * @param lat широта (обязательный параметр).
     * @param lon долгота (обязательный параметр).
     * @return объект {@link ResponseEntity}, содержащий данные о погоде ({@link Weather}) и статус ответа.
     */
    @GetMapping
    public ResponseEntity<Weather> getWeather(
            @RequestParam(value = "lat") double lat,
            @RequestParam(value = "lon") double lon) {
        Weather weather = weatherService.getWeather(lat, lon);
        return ResponseEntity.ok(weather);
    }
}

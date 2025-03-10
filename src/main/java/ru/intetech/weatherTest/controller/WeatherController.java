package ru.intetech.weatherTest.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.intetech.weatherTest.dto.Weather;
import ru.intetech.weatherTest.service.WeatherService;

@RestController
@RequestMapping(path = "/weather")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping
    public ResponseEntity<Weather> getWeather(
            @RequestParam(value = "lat") double lat,
            @RequestParam(value = "lon") double lon) {
        Weather weather = weatherService.getWeather(lat, lon);
        return ResponseEntity.ok(weather);
    }
}

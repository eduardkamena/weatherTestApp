package ru.intetech.weatherTest.service;

import ru.intetech.weatherTest.dto.Weather;

public interface WeatherService {

    Weather getWeather(double lat, double lon);
}

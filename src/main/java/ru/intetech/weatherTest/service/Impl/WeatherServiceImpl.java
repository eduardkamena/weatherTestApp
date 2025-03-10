package ru.intetech.weatherTest.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.intetech.weatherTest.dto.Weather;
import ru.intetech.weatherTest.service.WeatherService;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.url}")
    private String weatherUrl;

    @Value("${weather.api-key}")
    private String weatherApiKey;

    private final RestTemplate restTemplate;

    public WeatherServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public Weather getWeather(double lat, double lon) {
        Weather weather = restTemplate.exchange(
                weatherUrl,
                HttpMethod.GET,
                new HttpEntity<>(HttpHeaders.EMPTY),
                Weather.class,
                lat,
                lon,
                weatherApiKey
        ).getBody();

        return weather;
    }
}

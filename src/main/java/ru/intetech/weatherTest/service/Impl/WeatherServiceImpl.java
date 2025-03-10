package ru.intetech.weatherTest.service.Impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import ru.intetech.weatherTest.dto.Weather;
import ru.intetech.weatherTest.entity.WeatherRequestHistory;
import ru.intetech.weatherTest.repository.WeatherRequestRepository;
import ru.intetech.weatherTest.service.WeatherService;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.url}")
    private String weatherUrl;

    @Value("${weather.api-key}")
    private String weatherApiKey;

    private final RestTemplate restTemplate;
    private final WeatherRequestRepository weatherRequestRepository;

    public WeatherServiceImpl(RestTemplate restTemplate, WeatherRequestRepository weatherRequestRepository) {
        this.restTemplate = restTemplate;
        this.weatherRequestRepository = weatherRequestRepository;
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

        WeatherRequestHistory request = new WeatherRequestHistory();
        request.setLatitude(lat);
        request.setLongitude(lon);
        assert weather != null;
        request.setDescription(weather.getInfo().get(0).description());
        request.setRequestTime(LocalTime.parse(LocalTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss"))));
        weatherRequestRepository.save(request);

        return weather;
    }
}

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

/**
 * Реализация интерфейса {@link WeatherService}.
 * Предоставляет методы для получения данных о погоде с использованием внешнего API.
 * Также сохраняет историю запросов погоды в базу данных.
 */
@Service
public class WeatherServiceImpl implements WeatherService {

    @Value("${weather.url}")
    private String weatherUrl;

    @Value("${weather.api-key}")
    private String weatherApiKey;

    private final RestTemplate restTemplate;
    private final WeatherRequestRepository weatherRequestRepository;

    /**
     * Конструктор класса WeatherServiceImpl.
     *
     * @param restTemplate             объект {@link RestTemplate} для выполнения HTTP-запросов
     * @param weatherRequestRepository репозиторий для работы с историей запросов погоды
     */
    public WeatherServiceImpl(RestTemplate restTemplate, WeatherRequestRepository weatherRequestRepository) {
        this.restTemplate = restTemplate;
        this.weatherRequestRepository = weatherRequestRepository;
    }

    /**
     * Получает информацию о погоде для указанных географических координат.
     * Сохраняет запрос и результат в базу данных для истории.
     *
     * @param lat широта местоположения
     * @param lon долгота местоположения
     * @return объект {@link Weather}, содержащий информацию о погоде
     */
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

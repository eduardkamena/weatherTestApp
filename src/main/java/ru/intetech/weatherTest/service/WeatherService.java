package ru.intetech.weatherTest.service;

import ru.intetech.weatherTest.dto.Weather;

/**
 * Интерфейс сервиса для получения данных о погоде.
 * Определяет метод для получения информации о погоде по заданным координатам.
 */
public interface WeatherService {

    /**
     * Возвращает информацию о погоде для указанных географических координат.
     *
     * @param lat широта местоположения
     * @param lon долгота местоположения
     * @return объект {@link Weather}, содержащий информацию о погоде
     */
    Weather getWeather(double lat, double lon);
}

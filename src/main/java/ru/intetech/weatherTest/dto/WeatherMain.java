package ru.intetech.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Класс, представляющий основные метеорологические данные.
 * <p>
 * Этот класс используется для хранения данных о температуре и ощущаемой температуре,
 * полученных от внешнего API.
 * </p>
 *
 * @param temp      текущая температура в градусах Цельсия.
 * @param feelsLike ощущаемая температура в градусах Цельсия.
 */
public record WeatherMain(double temp, @JsonProperty("feels_like") double feelsLike) {
}

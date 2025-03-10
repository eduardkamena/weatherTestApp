package ru.intetech.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.*;

import java.util.List;

/**
 * Класс, представляющий данные о погоде.
 * <p>
 * Этот класс используется для хранения и передачи данных о погоде, полученных от внешнего API.
 * Он содержит информацию о погодных условиях (например, описание) и основные метеорологические данные
 * (например, температура, ощущаемая температура).
 * </p>
 */
@Getter
@Setter
@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
public class Weather {

    /**
     * Список объектов, содержащих информацию о погодных условиях.
     * <p>
     * Каждый объект {@link WeatherInfo} содержит описание погоды (например, "ясно", "облачно").
     * </p>
     */
    @JsonProperty("weather")
    private List<WeatherInfo> info;

    /**
     * Основные метеорологические данные.
     * <p>
     * Объект {@link WeatherMain} содержит такие данные, как температура, ощущаемая температура.
     * </p>
     */
    @JsonProperty("main")
    private WeatherMain main;
}

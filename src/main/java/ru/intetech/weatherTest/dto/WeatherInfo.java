package ru.intetech.weatherTest.dto;

/**
 * Класс, представляющий информацию о погодных условиях.
 * <p>
 * Этот класс используется для хранения описания погоды, полученного от внешнего API.
 * Например, "ясно", "облачно", "дождь".
 * </p>
 *
 * @param description описание погодных условий.
 */
public record WeatherInfo(String description) {
}

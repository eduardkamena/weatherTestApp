package ru.intetech.weatherTest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

/**
 * Сущность, представляющая историю запросов погоды.
 * <p>
 * Этот класс используется для хранения информации о запросах погоды в БД, включая координаты (широта и долгота),
 * описание погоды и время запроса.
 * </p>
 */
@Entity
@Data
@NoArgsConstructor
public class WeatherRequestHistory {

    /**
     * Уникальный идентификатор записи в базе данных.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Широта, указанная в запросе.
     */
    private double latitude;

    /**
     * Долгота, указанная в запросе.
     */
    private double longitude;

    /**
     * Описание погоды, полученное в ответе на запрос.
     */
    private String description;

    /**
     * Время выполнения запроса.
     */
    private LocalTime requestTime;
}

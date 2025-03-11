package ru.intetech.weatherTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.intetech.weatherTest.entity.WeatherRequestHistory;

import java.util.List;

/**
 * Репозиторий для работы с историей запросов погоды.
 * Предоставляет методы для доступа к данным о запросах погоды, хранящимся в базе данных.
 * Наследует функциональность {@link JpaRepository} для работы с сущностью {@link WeatherRequestHistory}.
 */
public interface WeatherRequestRepository extends JpaRepository<WeatherRequestHistory, Long> {

    /**
     * Возвращает список всех записей истории запросов погоды, отсортированных по идентификатору в порядке убывания.
     *
     * @return список объектов {@link WeatherRequestHistory}, отсортированных по id в порядке убывания
     */
    List<WeatherRequestHistory> findAllByOrderByIdDesc();
}

package ru.intetech.weatherTest.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.intetech.weatherTest.entity.WeatherRequestHistory;

import java.util.List;

public interface WeatherRequestRepository extends JpaRepository<WeatherRequestHistory, Long> {

    List<WeatherRequestHistory> findAllByOrderByIdDesc();
}

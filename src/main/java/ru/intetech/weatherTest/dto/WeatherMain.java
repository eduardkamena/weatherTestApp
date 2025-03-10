package ru.intetech.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record WeatherMain(double temp, @JsonProperty("feels_like") double feelsLike) {
}

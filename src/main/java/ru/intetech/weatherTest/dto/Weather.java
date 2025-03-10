package ru.intetech.weatherTest.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@EqualsAndHashCode(callSuper = false)
@ToString
@AllArgsConstructor
public class Weather {

    @JsonProperty("weather")
    private List<WeatherInfo> info;

    @JsonProperty("main")
    private WeatherMain main;

    public List<WeatherInfo> getInfo() {
        return info;
    }

    public WeatherMain getMain() {
        return main;
    }
}

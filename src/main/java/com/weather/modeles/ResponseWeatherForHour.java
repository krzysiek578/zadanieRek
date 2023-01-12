package com.weather.modeles;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalTime;


@Getter
@Setter
public class ResponseWeatherForHour {
    private LocalTime time;
    private double temperature;
    private double snow;
    private double rain;
    private double windSpeed;
}

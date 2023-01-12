package com.weather.webClient.weather.clientDTO;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;


@Getter
@Setter
@RequiredArgsConstructor
public class ClientWeatherHourlyDTO {
    private final List<LocalDateTime> time;
    private final List<Double> temperature_2m;
    private final List<Double> windspeed_10m;
    private final List<Double> rain;
    private final List<Double> snowfall;
}

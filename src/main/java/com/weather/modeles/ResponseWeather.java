package com.weather.modeles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class ResponseWeather {
    private LocalDate date;
    private List<ResponseWeatherForHour> datapoints = new ArrayList<>();
}



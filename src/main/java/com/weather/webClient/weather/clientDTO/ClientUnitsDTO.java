package com.weather.webClient.weather.clientDTO;

import lombok.AllArgsConstructor;
import lombok.Getter;



@AllArgsConstructor
@Getter
public class ClientUnitsDTO {
    private final String time;
    private final String temperature_2m;
    private final String windspeed_10m;
    private final String rain;
    private final String snowfall;
}

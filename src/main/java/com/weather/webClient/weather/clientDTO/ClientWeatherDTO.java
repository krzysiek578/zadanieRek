package com.weather.webClient.weather.clientDTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ClientWeatherDTO {
    private ClientUnitsDTO hourly_units;
    private ClientWeatherHourlyDTO hourly;
}

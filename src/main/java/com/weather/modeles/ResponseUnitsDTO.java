package com.weather.modeles;

import lombok.Getter;
import lombok.Setter;


@Setter
@Getter
public class ResponseUnitsDTO {
    private String time;
    private String temperature;
    private String windspeed10m;
    private String rain;
    private String snowFall;
}

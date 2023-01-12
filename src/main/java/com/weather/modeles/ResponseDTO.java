package com.weather.modeles;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class ResponseDTO {
    private ResponseUnitsDTO units;
    private List<ResponseWeather> forecast = new ArrayList<>();
}

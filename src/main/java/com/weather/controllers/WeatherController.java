package com.weather.controllers;


import com.weather.services.WeatherService;
import com.weather.mappers.MapperClientToResponse;
import com.weather.modeles.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final MapperClientToResponse mapperClientToResponse;

    //?szerokosc=42&wysokosc=60 Dodaj request param
    @GetMapping(value = "/weather{latitude}{longitude}")
    public ResponseDTO lookWeather(@RequestParam("latitude") final double lat, @RequestParam("longitude") final double lon) {
        return mapperClientToResponse.mapToResponseObject(weatherService.getWeather(lat, lon));
    }
}

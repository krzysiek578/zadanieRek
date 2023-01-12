package com.weather.controllers;


import com.weather.services.WeatherService;
import com.weather.mappers.WeatherMapper;
import com.weather.modeles.ResponseDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;
    private final WeatherMapper weatherMapper;

    @GetMapping(value = "/weather")
    public ResponseDTO lookWeather(@RequestParam("latitude") final double lat, @RequestParam("longitude") final double lon) {
        return weatherMapper.mapToResponseObject(weatherService.getWeather(lat, lon));
    }
}

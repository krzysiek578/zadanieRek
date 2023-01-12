package com.weather.services;

import com.weather.webClient.weather.clientDTO.ClientWeatherDTO;
import com.weather.webClient.weather.WeatherClient;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class WeatherService {

    private final WeatherClient weatherClient;

    public ClientWeatherDTO getWeather(final Double latitude, final Double longitude) {
        return weatherClient.getWeatherForLatitudeLongitude(latitude, longitude);
    }


}

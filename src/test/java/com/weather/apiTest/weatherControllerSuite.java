package com.weather.apiTest;

import com.weather.controllers.WeatherController;
import com.weather.services.WeatherService;
import com.weather.mappers.WeatherMapper;
import com.weather.webClient.weather.clientDTO.ClientWeatherDTO;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@SpringBootTest
public class weatherControllerSuite {

    @InjectMocks
    WeatherController weatherController;

    @Mock
    WeatherService weatherService;

    @Mock
    ClientWeatherDTO clientWeatherDTO;

    @Mock
    WeatherMapper weatherMapper;


    @Test
    public void getWeatherFromControllerTest() {
        //Given
        weatherController.lookWeather(23.00, 23.00);
        //Then
        verify(weatherService, times(1)).getWeather(23.00, 23.00);
    }
}

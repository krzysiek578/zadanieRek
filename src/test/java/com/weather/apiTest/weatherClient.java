package com.weather.apiTest;

import com.weather.webClient.weather.WeatherClient;
import com.weather.webClient.weather.clientDTO.ClientWeatherDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.Assert;


@SpringBootTest
public class weatherClient {

    @Mock
    WeatherClient weatherClient;


    @Test
    public void getWeatherForLatitudeLongitude() {
        //Given
        final ClientWeatherDTO clientWeatherDTO = weatherClient.getWeatherForLatitudeLongitude(23.00, 23.00);
        //When
        Assertions.assertNull(clientWeatherDTO);
    }
}

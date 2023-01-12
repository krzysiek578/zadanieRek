package com.weather.apiTest;

import com.weather.mappers.MapperClientToResponse;
import com.weather.modeles.ResponseDTO;
import com.weather.modeles.ResponseWeather;
import com.weather.modeles.ResponseWeatherForHour;
import com.weather.webClient.weather.clientDTO.ClientUnitsDTO;
import com.weather.webClient.weather.clientDTO.ClientWeatherDTO;
import com.weather.webClient.weather.clientDTO.ClientWeatherHourlyDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class mapperClientToResponseSuite {

    @Autowired
    MapperClientToResponse mapperClientToResponse;


    @Test
    public void testMapClientWeatherDTOToResponseDTOUnits() {
        //Given
        ClientWeatherHourlyDTO clientWeatherHourlyDTO = new ClientWeatherHourlyDTO(
                List.of(LocalDateTime.of(2022, 11, 12, 13, 30)),
                List.of(2.00),
                List.of(20.00),
                List.of(1.0),
                List.of(0.0)
        );
        ClientUnitsDTO clientUnitsDTO = new ClientUnitsDTO("time", "temp", "wind", "rain", "snow");
        ClientWeatherDTO clientWeatherDTO = new ClientWeatherDTO();
        clientWeatherDTO.setHourly(clientWeatherHourlyDTO);
        clientWeatherDTO.setHourly_units(clientUnitsDTO);
        //When
        ResponseDTO responseDTO = mapperClientToResponse.mapToResponseObject(clientWeatherDTO);
        //Then
        Assertions.assertNotNull(responseDTO);
        Assertions.assertNotNull(responseDTO.getUnits());
        Assertions.assertEquals(1, responseDTO.getForecast().size());
        Assertions.assertEquals("wind", responseDTO.getUnits().getWindspeed10m());
        Assertions.assertEquals("time", responseDTO.getUnits().getTime());
        Assertions.assertEquals("temp", responseDTO.getUnits().getTemperature());
        Assertions.assertEquals("rain", responseDTO.getUnits().getRain());
        Assertions.assertEquals("snow", responseDTO.getUnits().getSnowFall());


    }

    @Test
    public void testMapClientWeatherDTOToResponseDTOWeather() {
        //Given
        ClientWeatherHourlyDTO clientWeatherHourlyDTO = new ClientWeatherHourlyDTO(
                List.of(LocalDateTime.of(2022, 11, 12, 13, 30)),
                List.of(2.00),
                List.of(20.00),
                List.of(1.0),
                List.of(0.0)
        );
        ClientUnitsDTO clientUnitsDTO = new ClientUnitsDTO("time", "temp", "wind", "rain", "snow");
        ClientWeatherDTO clientWeatherDTO = new ClientWeatherDTO();
        clientWeatherDTO.setHourly(clientWeatherHourlyDTO);
        clientWeatherDTO.setHourly_units(clientUnitsDTO);
        //When
        ResponseDTO responseDTO = mapperClientToResponse.mapToResponseObject(clientWeatherDTO);
        //Then
        ResponseWeather responseWeatherAfterMaping = responseDTO.getForecast().get(0);

        Assertions.assertEquals(2022, responseWeatherAfterMaping.getDate().getYear());
        Assertions.assertEquals(11, responseWeatherAfterMaping.getDate().getMonthValue());
        Assertions.assertEquals(12, responseWeatherAfterMaping.getDate().getDayOfMonth());
        Assertions.assertEquals(1, responseWeatherAfterMaping.getDatapoints().size());

    }

    @Test
    public void testMapClientWeatherDTOToResponseDTOWeatherForHour() {
        //Given
        ClientWeatherHourlyDTO clientWeatherHourlyDTO = new ClientWeatherHourlyDTO(
                List.of(LocalDateTime.of(2022, 11, 12, 13, 30)),
                List.of(2.00), // temp
                List.of(20.00), // wind
                List.of(1.0), // rain
                List.of(0.0) // snow
        );
        ClientUnitsDTO clientUnitsDTO = new ClientUnitsDTO("time", "temp", "wind", "rain", "snow");
        ClientWeatherDTO clientWeatherDTO = new ClientWeatherDTO();
        clientWeatherDTO.setHourly(clientWeatherHourlyDTO);
        clientWeatherDTO.setHourly_units(clientUnitsDTO);
        //When
        ResponseDTO responseDTO = mapperClientToResponse.mapToResponseObject(clientWeatherDTO);
        //Then

        ResponseWeather responseWeatherAfterMaping = responseDTO.getForecast().get(0);
        ResponseWeatherForHour responseWeatherForHour = responseWeatherAfterMaping.getDatapoints().get(0);

        Assertions.assertEquals(13, responseWeatherForHour.getTime().getHour());
        Assertions.assertEquals(30, responseWeatherForHour.getTime().getMinute());
        Assertions.assertEquals(2, responseWeatherForHour.getTemperature());
        Assertions.assertEquals(20.00, responseWeatherForHour.getWindSpeed());
        Assertions.assertEquals(1, responseWeatherForHour.getRain());
        Assertions.assertEquals(0, responseWeatherForHour.getSnow());

    }
}

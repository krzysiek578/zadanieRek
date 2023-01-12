package com.weather.mappers;

import com.weather.modeles.ResponseDTO;
import com.weather.modeles.ResponseUnitsDTO;
import com.weather.modeles.ResponseWeather;
import com.weather.modeles.ResponseWeatherForHour;
import com.weather.webClient.weather.clientDTO.ClientUnitsDTO;
import com.weather.webClient.weather.clientDTO.ClientWeatherDTO;
import com.weather.webClient.weather.clientDTO.ClientWeatherHourlyDTO;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Component
public class MapperClientToResponse implements MapperResponseInterface<ResponseDTO, ClientWeatherDTO> {

    @Override
    public ResponseDTO mapToResponseObject(final ClientWeatherDTO clientWeatherDTO) {


        List<ResponseWeather> weathers = mapResponseDTOSFromClientWeatherDTO(clientWeatherDTO);
        ResponseUnitsDTO responseUnitsDTO = mapResponseUnitsDTOFromClientWeatherDTO(clientWeatherDTO);

        return createResponsDTOFromWeathersAndReponseUnits(weathers, responseUnitsDTO);
    }

    private static ResponseDTO createResponsDTOFromWeathersAndReponseUnits(final List<ResponseWeather> weathers, ResponseUnitsDTO responseUnitsDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setForecast(weathers);
        responseDTO.setUnits(responseUnitsDTO);
        return responseDTO;
    }

    private List<ResponseWeather> mapResponseDTOSFromClientWeatherDTO(final ClientWeatherDTO clientWeatherDTO) {
        List<ResponseWeather> result = new ArrayList<>();
        ClientWeatherHourlyDTO clientWeatherHourlyDTO = clientWeatherDTO.getHourly();
        //ArrayList Client
        List<LocalDateTime> clientWeatherDate = clientWeatherHourlyDTO.getTime();
        List<Double> clientWeatherTemp = clientWeatherHourlyDTO.getTemperature_2m();
        List<Double> clientWeatherRain = clientWeatherHourlyDTO.getRain();
        List<Double> clientWeatherSnow = clientWeatherHourlyDTO.getSnowfall();
        List<Double> clientWeatherWindSpeed = clientWeatherHourlyDTO.getWindspeed_10m();


        for (LocalDateTime dateTime : clientWeatherDate) {
            ResponseWeather responseWeather = new ResponseWeather();
            responseWeather.setDate(LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth()));
            ResponseWeatherForHour responseWeatherForHour = new ResponseWeatherForHour();
            responseWeatherForHour.setTime(dateTime.toLocalTime());
            int index = clientWeatherDate.indexOf(dateTime);
            responseWeatherForHour.setRain(clientWeatherRain.get(index));
            responseWeatherForHour.setTemperature(clientWeatherTemp.get(index));
            responseWeatherForHour.setSnow(clientWeatherSnow.get(index));
            responseWeatherForHour.setWindSpeed(clientWeatherWindSpeed.get(index));
            responseWeather.getDatapoints().add(responseWeatherForHour);
            result.add(responseWeather);
        }
        return result;
    }

    private static ResponseUnitsDTO mapResponseUnitsDTOFromClientWeatherDTO(ClientWeatherDTO clientWeatherDTO) {
        ClientUnitsDTO clientUnitsDTO = clientWeatherDTO.getHourly_units();
        ResponseUnitsDTO responseUnitsDTO = new ResponseUnitsDTO();
        responseUnitsDTO.setWindspeed10m(clientUnitsDTO.getWindspeed_10m());
        responseUnitsDTO.setRain(clientUnitsDTO.getRain());
        responseUnitsDTO.setTime(clientUnitsDTO.getTime());
        responseUnitsDTO.setTemperature(clientUnitsDTO.getTemperature_2m());
        responseUnitsDTO.setSnowFall(clientUnitsDTO.getSnowfall());
        return responseUnitsDTO;
    }


}

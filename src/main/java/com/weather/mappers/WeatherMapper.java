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
public class WeatherMapper implements Mapper<ResponseDTO, ClientWeatherDTO> {

    @Override
    public ResponseDTO mapToResponseObject(final ClientWeatherDTO clientWeatherDTO) {


        final List<ResponseWeather> weathers = mapResponseDTOSFromClientWeatherDTO(clientWeatherDTO);
        final ResponseUnitsDTO responseUnitsDTO = mapResponseUnitsDTOFromClientWeatherDTO(clientWeatherDTO);

        return createResponsDTOFromWeathersAndReponseUnits(weathers, responseUnitsDTO);
    }

    private static ResponseDTO createResponsDTOFromWeathersAndReponseUnits(final List<ResponseWeather> weathers, final ResponseUnitsDTO responseUnitsDTO) {
        final ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setForecast(weathers);
        responseDTO.setUnits(responseUnitsDTO);
        return responseDTO;
    }

    private List<ResponseWeather> mapResponseDTOSFromClientWeatherDTO(final ClientWeatherDTO clientWeatherDTO) {
        final List<ResponseWeather> result = new ArrayList<>();
        final ClientWeatherHourlyDTO clientWeatherHourlyDTO = clientWeatherDTO.getHourly();
        //ArrayList Client
        final List<LocalDateTime> clientWeatherDate = clientWeatherHourlyDTO.getTime();
        final List<Double> clientWeatherTemp = clientWeatherHourlyDTO.getTemperature_2m();
        final List<Double> clientWeatherRain = clientWeatherHourlyDTO.getRain();
        final List<Double> clientWeatherSnow = clientWeatherHourlyDTO.getSnowfall();
        final List<Double> clientWeatherWindSpeed = clientWeatherHourlyDTO.getWindspeed_10m();


        for (LocalDateTime dateTime : clientWeatherDate) {
            mapToResponsWeather(result, clientWeatherDate, clientWeatherTemp, clientWeatherRain, clientWeatherSnow, clientWeatherWindSpeed, dateTime);
        }
        return result;
    }

    private static void mapToResponsWeather(final List<ResponseWeather> result, final List<LocalDateTime> clientWeatherDate, final List<Double> clientWeatherTemp, final List<Double> clientWeatherRain, final List<Double> clientWeatherSnow, final List<Double> clientWeatherWindSpeed, final LocalDateTime dateTime) {
        final ResponseWeather responseWeather = new ResponseWeather();

        responseWeather.setDate(LocalDate.of(dateTime.getYear(), dateTime.getMonth(), dateTime.getDayOfMonth()));
        final ResponseWeatherForHour responseWeatherForHour = new ResponseWeatherForHour();

        responseWeatherForHour.setTime(dateTime.toLocalTime());
        final int index = clientWeatherDate.indexOf(dateTime);

        responseWeatherForHour.setRain(clientWeatherRain.get(index));
        responseWeatherForHour.setTemperature(clientWeatherTemp.get(index));
        responseWeatherForHour.setSnow(clientWeatherSnow.get(index));
        responseWeatherForHour.setWindSpeed(clientWeatherWindSpeed.get(index));
        responseWeather.getDatapoints().add(responseWeatherForHour);

        result.add(responseWeather);
    }

    private static ResponseUnitsDTO mapResponseUnitsDTOFromClientWeatherDTO(final ClientWeatherDTO clientWeatherDTO) {
        final ClientUnitsDTO clientUnitsDTO = clientWeatherDTO.getHourly_units();

        final ResponseUnitsDTO responseUnitsDTO = new ResponseUnitsDTO();
        responseUnitsDTO.setWindspeed10m(clientUnitsDTO.getWindspeed_10m());
        responseUnitsDTO.setRain(clientUnitsDTO.getRain());
        responseUnitsDTO.setTime(clientUnitsDTO.getTime());
        responseUnitsDTO.setTemperature(clientUnitsDTO.getTemperature_2m());
        responseUnitsDTO.setSnowFall(clientUnitsDTO.getSnowfall());

        return responseUnitsDTO;
    }


}

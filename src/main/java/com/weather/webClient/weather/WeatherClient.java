package com.weather.webClient.weather;


import com.weather.webClient.weather.clientDTO.ClientWeatherDTO;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import static org.springframework.web.reactive.function.client.WebClient.Builder;


@Component
@RequiredArgsConstructor
public class WeatherClient {

    @Value("${external.endpoint.api.weather:default}")
    private String endpoint_address;
    private static final String LATITUDE = "latitude";
    private static final String LONGITUDE = "longitude";
    private static final String HOURLY = "hourly";
    private static final String PARAMS_HOURLY = "temperature_2m,windspeed_10m,rain,snowfall";
    private final Builder webClientBuilder;

    public ClientWeatherDTO getWeatherForLatitudeLongitude(final Double latitude, final Double longitude) {
        return webClientBuilder
                .baseUrl(endpoint_address)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam(LATITUDE, String.valueOf(latitude))
                        .queryParam(LONGITUDE, String.valueOf(longitude))
                        .queryParam(HOURLY, PARAMS_HOURLY)
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ClientWeatherDTO.class)
                .blockFirst();
    }
}

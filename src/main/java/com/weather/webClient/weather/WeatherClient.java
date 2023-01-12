package com.weather.webClient.weather;



import com.weather.webClient.weather.clientDTO.ClientWeatherDTO;
import lombok.RequiredArgsConstructor;
import lombok.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;

import static org.springframework.web.reactive.function.client.WebClient.Builder;



@Service
@RequiredArgsConstructor
public class WeatherClient{


    private final Builder webClientBuilder;
    private static final String ENDPOINT_ADDRESS = "https://api.open-meteo.com/v1/forecast";

    public ClientWeatherDTO getWeatherForLatitudeLongitude(final Double latitude, final Double longitude) {
        return webClientBuilder
                .baseUrl(ENDPOINT_ADDRESS)
                .build()
                .get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", String.valueOf(latitude))
                        .queryParam("longitude", String.valueOf(longitude))
                        .queryParam("hourly", "temperature_2m,windspeed_10m,rain,snowfall")
                        .build())
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToFlux(ClientWeatherDTO.class)
                .blockFirst();
    }
}

package com.weather.apiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.controllers.WeatherController;
import com.weather.modeles.ResponseDTO;
import com.weather.webClient.weather.WeatherClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.reactive.function.client.WebClient;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.web.reactive.function.client.WebClient.Builder;

@SpringBootTest
@AutoConfigureMockMvc
public class apiWeatherSuite {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    WeatherController weatherController;

    @Autowired
    WeatherClient weatherClient;


    @Test
    public void testEndpointGet() throws Exception {


        final MvcResult rt = mockMvc.perform(get("https://localhost:8888/weather?latitude=40&longitude=23")
                        .contentType("application/json"))
                .andExpect(status().isOk())
                .andReturn();

        final String json = rt.getResponse().getContentAsString();
        final ResponseDTO responseDTO = objectMapper.readValue(json, ResponseDTO.class);

        Assertions.assertNotNull(responseDTO);
        Assertions.assertEquals("mm", responseDTO.getUnits().getRain());
        Assertions.assertEquals("km/h", responseDTO.getUnits().getWindspeed10m());

        Assertions.assertNotNull(responseDTO.getForecast());
        Assertions.assertEquals(168, responseDTO.getForecast().size());
        Assertions.assertNotNull(responseDTO.getForecast().get(0).getDate());
        Assertions.assertEquals(1, responseDTO.getForecast().get(0).getDatapoints().size());
        Assertions.assertInstanceOf(Double.class, responseDTO.getForecast().get(0).getDatapoints().get(0).getSnow());
    }
}

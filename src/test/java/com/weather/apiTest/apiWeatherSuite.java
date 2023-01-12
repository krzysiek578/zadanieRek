package com.weather.apiTest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.weather.modeles.ResponseDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
public class apiWeatherSuite {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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

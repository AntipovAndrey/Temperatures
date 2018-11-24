package ru.andrey.sensor.temperaturesensor.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.andrey.sensor.temperaturesensor.service.TemperatureService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class TemperatureControllerTest {

    private MockMvc mvc;

    @Mock
    private TemperatureService service;

    @InjectMocks
    private TemperatureController controller;

    private RequestBuilder requestBuilder;

    private String validRequestBody = "{\n" +
            "  \"lat\": 87.34,\n" +
            "  \"lon\": -12.67,\n" +
            "  \"scale\": \"F\",\n" +
            "  \"temperature\" : 36.6\n" +
            "}\n";

    private String invalidRequestBody = "{\n" +
            "  \"lat\": 2000,\n" +
            "  \"lon\": -12.7,\n" +
            "  \"scale\": \"normal\",\n" +
            "  \"temperature\" : 36.6\n" +
            "}\n";

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mvc = MockMvcBuilders
                .standaloneSetup(controller)
                .build();
    }

    @Test
    void when_request_is_valid_then_store() throws Exception {
        mvc.perform(post("/temperatures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(validRequestBody))
                .andExpect(status().isCreated());

        verify(service).store(any());
    }

    @Test
    void when_request_is_valid_then_send_400() throws Exception {
        mvc.perform(post("/temperatures")
                .contentType(MediaType.APPLICATION_JSON)
                .content(invalidRequestBody))
                .andExpect(status().isBadRequest());

        verify(service, never()).store(any());
    }
}
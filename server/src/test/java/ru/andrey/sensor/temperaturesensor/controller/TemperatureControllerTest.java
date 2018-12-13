package ru.andrey.sensor.temperaturesensor.controller;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import ru.andrey.sensor.temperaturesensor.service.TemperatureService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

class TemperatureControllerTest {

    @Mock
    private TemperatureService service;

    @InjectMocks
    private TemperatureController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Nested
    class ValidRequest {
        private String requestBody = "{\n" +
                "  \"lat\": 87.34,\n" +
                "  \"lon\": -12.67,\n" +
                "  \"scale\": \"F\",\n" +
                "  \"temperature\" : 36.6\n" +
                "}\n";

        @Test
        void when_request_is_valid_then_store() throws Exception {
            //@formatter:off
            given()
                .standaloneSetup(controller)
                .body(requestBody)
                .contentType(MediaType.APPLICATION_JSON_VALUE)
            .when()
                .post("/temperatures")
            .then()
                .statusCode(HttpStatus.SC_CREATED);
            //@formatter:on
            verify(service).store(any());
        }
    }

    @Nested
    class InvalidRequest {
        private String requestBody = "{\n" +
                "  \"lat\": 2000,\n" +
                "  \"lon\": -12.7,\n" +
                "  \"temperature\" : 36.6\n" +
                "}\n";

        @Test
        void when_request_is_invalid_then_send_400() throws Exception {
            //@formatter:off
                given()
                    .standaloneSetup(controller)
                    .body(requestBody)
                    .contentType(MediaType.APPLICATION_JSON_VALUE)
                .when()
                    .post("/temperatures")
                .then()
                    .statusCode(HttpStatus.SC_BAD_REQUEST);
            //@formatter:on
            verify(service, never()).store(any());
        }
    }
}

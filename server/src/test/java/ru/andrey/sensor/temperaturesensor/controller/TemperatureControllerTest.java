package ru.andrey.sensor.temperaturesensor.controller;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import ru.andrey.sensor.temperaturesensor.service.TemperatureService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.core.StringEndsWith.endsWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;

@SpringBootTest
class TemperatureControllerTest {

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private TemperatureService service;

    @Autowired
    private TemperatureController controller;

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

    @Test
    void when_request_is_valid_then_store() throws Exception {
        //@formatter:off
        given()
            .standaloneSetup(controller)
            .body(validRequestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .post("/temperatures")
        .then()
            .statusCode(HttpStatus.SC_CREATED);
        //@formatter:on
        verify(service).store(any());
    }

    @Test
    void when_request_is_invalid_then_send_400() throws Exception {
        //@formatter:off
        given()
            .standaloneSetup(controller)
            .body(invalidRequestBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .post("/temperatures")
        .then()
            .statusCode(HttpStatus.SC_BAD_REQUEST);
        //@formatter:on
        verify(service, never()).store(any());
    }

    @Test
    void when_no_coordinates_provided_expect_plain_self_link() throws Exception {
        //@formatter:off
        given()
            .webAppContextSetup(context)
        .when()
            .get("/temperatures")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("_links.self.href", endsWith("/temperatures"));
        //@formatter:on
    }

    @Test
    void when_coordinates_are_provided_expect_parametrized_self_link() throws Exception {
        //@formatter:off
        given()
            .webAppContextSetup(context)
        .when()
            .get("/temperatures?lon=42.0&lat=10.0")
            .prettyPeek()
        .then()
            .statusCode(HttpStatus.SC_OK)
            .body("_links.self.href", endsWith("/temperatures?lon=42.0&lat=10.0"));
        //@formatter:on
    }
}

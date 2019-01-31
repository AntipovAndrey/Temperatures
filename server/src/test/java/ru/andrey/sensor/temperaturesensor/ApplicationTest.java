package ru.andrey.sensor.temperaturesensor;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.web.context.WebApplicationContext;
import ru.andrey.sensor.temperaturesensor.service.TemperatureService;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.core.StringEndsWith.endsWith;

@SpringBootTest
class ApplicationTest {

    @MockBean
    private TemperatureService service;

    @Autowired
    private WebApplicationContext context;

    private String invalidBody = "{\n" +
            "  \"lat\": 2000,\n" +
            "  \"lon\": -12.7,\n" +
            "  \"temperature\" : 36.6\n" +
            "}\n";

    @Test
    void when_coordinate_is_invalid_then_send_error_response() throws Exception {
        //@formatter:off
        given()
            .webAppContextSetup(context)
            .body(invalidBody)
            .contentType(MediaType.APPLICATION_JSON_VALUE)
        .when()
            .post("/temperatures")
            .prettyPeek()
        .then()
            .body("lat[0]", containsString("must"))
            .statusCode(HttpStatus.SC_BAD_REQUEST);
        //@formatter:on
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
            .body("_links.self.href", allOf(containsString("lon=42"), containsString("lat=10")));
        //@formatter:on
    }
}

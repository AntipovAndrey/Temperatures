package ru.andrey.sensor.temperaturesensor.service.mapping;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.CoordinateRequest;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse;
import ru.andrey.sensor.temperaturesensor.model.Scale;
import ru.andrey.sensor.temperaturesensor.model.Temperature;

import java.math.BigDecimal;
import java.time.Instant;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.mockito.Mockito.*;

class TemperatureDtoMapperTest {

    @Mock
    private TemperatureProperties defaults;

    @Spy
    private CoordinateDtoMapper coordinateDtoMapper;

    @InjectMocks
    private TemperatureDtoMapper mapper;

    private Scale defaultScale;

    private TemperatureRequest temperatureRequest;

    private CoordinateRequest coordinateRequest;

    @BeforeEach
    void setUp() {
        coordinateDtoMapper = new CoordinateDtoMapper();

        MockitoAnnotations.initMocks(this);

        defaultScale = Scale.C;

        when(defaults.getDefaults()).thenReturn(new TemperatureProperties.Defaults() {
            @Override
            public Scale getScale() {
                return defaultScale;
            }
        });

        coordinateRequest = new CoordinateRequest(BigDecimal.ONE, BigDecimal.TEN);

        temperatureRequest = new TemperatureRequest(coordinateRequest, "F", 42);
    }

    @Test
    void when_no_scale_provided_then_use_default_and_convert() {
        temperatureRequest.setScale("");

        Temperature model = mapper.toModel(temperatureRequest);

        verify(defaults).getDefaults();

        double converted = defaultScale.toCelsius(temperatureRequest.getTemperature());
        assertThat(model.getTemperature(), closeTo(converted, 0.1));
    }

    @Test
    void when_scale_provided_then_convert() {
        Temperature model = mapper.toModel(temperatureRequest);

        verify(defaults, never()).getDefaults();

        double converted = Scale.valueOf(temperatureRequest.getScale())
                .toCelsius(temperatureRequest.getTemperature());
        assertThat(model.getTemperature(), closeTo(converted, 0.1));
    }

    @Test
    void test_mapped_model_contains_all_fields() {
        Instant now = Instant.now();
        temperatureRequest.setScale(defaultScale.toString());
        Temperature model = mapper.toModel(temperatureRequest);

        assertThat(model.getTemperature(), closeTo(temperatureRequest.getTemperature(), 0.0001));
        assertThat(model.getCoordinate().getLat(), closeTo(coordinateRequest.getLat().doubleValue(), 0.0001));
        assertThat(model.getCoordinate().getLon(), closeTo(coordinateRequest.getLon().doubleValue(), 0.0001));
        assertThat(model.getTime().plusMillis(1).isAfter(now), is(true));
    }

    @Test
    void test_mapped_response_contains_all_fields() {
        temperatureRequest.setScale(defaultScale.toString());
        TemperatureResponse res = mapper.fromModel(mapper.toModel(temperatureRequest));

        assertThat(res.getTemperature(), closeTo(temperatureRequest.getTemperature(), 0.0001));
        assertThat(res.getLat(), closeTo(coordinateRequest.getLat().doubleValue(), 0.0001));
        assertThat(res.getLon(), closeTo(coordinateRequest.getLon().doubleValue(), 0.0001));
    }
}

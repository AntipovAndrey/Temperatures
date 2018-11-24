package ru.andrey.sensor.temperaturesensor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.model.Scale;
import ru.andrey.sensor.temperaturesensor.model.Temperature;
import ru.andrey.sensor.temperaturesensor.repository.TemperatureRepository;

import java.math.BigDecimal;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.number.IsCloseTo.closeTo;
import static org.mockito.Mockito.*;

class TemperatureServiceTest {

    @Mock
    private TemperatureProperties defaults;

    @Mock
    private TemperatureRepository repository;

    @Mock
    private LocationService locationService;

    private TemperatureRequest temperatureRequest;

    @InjectMocks
    private TemperatureService temperatureService;

    private Scale defaultScale;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);

        defaultScale = Scale.C;

        when(defaults.getDefaults()).thenReturn(new TemperatureProperties.Defaults() {
            @Override
            public Scale getScale() {
                return defaultScale;
            }
        });

        when(locationService.findCityByCoordinates(anyDouble(), anyDouble())).thenReturn("CIty");

        temperatureRequest = new TemperatureRequest() {{
            setLat(BigDecimal.valueOf(0));
            setLon(BigDecimal.valueOf(0));
            setTemperature(42D);
            setScale("F");
        }};
    }

    @Test
    void when_no_scale_provided_then_use_default_and_store() {
        temperatureRequest.setScale("");

        temperatureService.store(temperatureRequest);

        ArgumentCaptor<Temperature> argumentCaptor = ArgumentCaptor.forClass(Temperature.class);
        verify(repository).save(argumentCaptor.capture());

        double converted = defaultScale.toCelsius(temperatureRequest.getTemperature());
        assertThat(argumentCaptor.getValue().getTemperature(), closeTo(converted, 0.1));
    }

    @Test
    void when_scale_provided_then_convert_and_store() {
        temperatureService.store(temperatureRequest);

        ArgumentCaptor<Temperature> argumentCaptor = ArgumentCaptor.forClass(Temperature.class);
        verify(repository).save(argumentCaptor.capture());

        verify(defaults, never()).getDefaults();

        double converted = Scale.valueOf(temperatureRequest.getScale())
                .toCelsius(temperatureRequest.getTemperature());
        assertThat(argumentCaptor.getValue().getTemperature(), closeTo(converted, 0.1));
    }
}
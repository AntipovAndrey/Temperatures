package ru.andrey.sensor.temperaturesensor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.CoordinateRequest;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse;
import ru.andrey.sensor.temperaturesensor.model.Coordinate;
import ru.andrey.sensor.temperaturesensor.model.Scale;
import ru.andrey.sensor.temperaturesensor.model.Temperature;
import ru.andrey.sensor.temperaturesensor.repository.TemperatureRepository;
import ru.andrey.sensor.temperaturesensor.service.mapping.CoordinateDtoMapper;
import ru.andrey.sensor.temperaturesensor.service.mapping.TemperatureDtoMapper;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class TemperatureServiceTest {

    @Mock
    private TemperatureRepository repository;

    @Mock
    private LocationService locationService;

    @Spy
    private TemperatureDtoMapper mapper;

    @Mock
    private TemperatureProperties.Defaults defaults;

    @InjectMocks
    private TemperatureService service;

    private List<Temperature> temperatures;

    @BeforeEach
    void setUp() {
        TemperatureProperties temperatureProperties = Mockito.mock(TemperatureProperties.class);
        mapper = new TemperatureDtoMapper(temperatureProperties, new CoordinateDtoMapper());
        MockitoAnnotations.initMocks(this);
        temperatures = DoubleStream.iterate(0D, d -> d + 1.5)
                .mapToObj(d -> new Temperature(
                        null, new Coordinate(d, d * 2), 3 * d, null, Instant.now()
                ))
                .limit(10)
                .collect(Collectors.toList());

        when(defaults.getScale()).thenReturn(Scale.C);
        when(temperatureProperties.getDefaults()).thenReturn(defaults);
        when(temperatureProperties.getMaxRecords()).thenReturn(temperatures.size());
        when(locationService.findCityByCoordinates(any())).thenReturn(Optional.empty());
        when(repository.findAll((Pageable) any())).thenReturn(new PageImpl<>(temperatures));
    }

    @Test
    void test_list_of_all_temperatures_was_transformed() {
        List<TemperatureResponse> latest = service.getLatest();

        verify(repository).findAll((Pageable) any());
        verify(mapper, atLeast(1)).fromModel(any());

        assertThat(latest.size(), is(temperatures.size()));
    }

    @Test
    void test_service_stores_temperature() {
        TemperatureRequest temperatureRequest = new TemperatureRequest(
                new CoordinateRequest(BigDecimal.ONE, BigDecimal.TEN), "F", 42);

        service.store(temperatureRequest);

        verify(mapper).toModel(temperatureRequest);
        verify(locationService).findCityByCoordinates(any());
        verify(repository).save(any());
    }

    @Test
    void when_no_city_found_then_return_all() {
        List<TemperatureResponse> latest = service.getLatestInCity(13, 37);

        verify(repository).findAll((Pageable) any());
        verify(repository, never()).findByCity(anyString(), any());
    }
}
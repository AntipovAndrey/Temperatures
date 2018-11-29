package ru.andrey.sensor.temperaturesensor.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse;
import ru.andrey.sensor.temperaturesensor.repository.TemperatureRepository;
import ru.andrey.sensor.temperaturesensor.service.mapping.TemperatureDtoMapper;

class TemperatureServiceTest {

    @Mock
    private TemperatureRepository repository;

    @Mock
    private LocationService locationService;

    @Mock
    private TemperatureDtoMapper mapper;

    @Mock
    private TemperatureProperties temperatureProperties;

    @InjectMocks
    private TemperatureService temperatureService;

    private TemperatureRequest temperatureRequest;
    private TemperatureResponse response;

    @BeforeEach
    void setUp() {

    }

    @Test
    void todo() {

    }
}
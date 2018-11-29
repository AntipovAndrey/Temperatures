package ru.andrey.sensor.temperaturesensor.service.mapping;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse;
import ru.andrey.sensor.temperaturesensor.model.Scale;
import ru.andrey.sensor.temperaturesensor.model.Temperature;

import java.time.Instant;

@Component
public class TemperatureDtoMapper implements DuplexMapper<TemperatureRequest, TemperatureResponse, Temperature> {

    private final TemperatureProperties temperatureProperties;
    private final CoordinateDtoMapper coordinatesMapper;

    @Autowired
    public TemperatureDtoMapper(TemperatureProperties temperatureProperties, CoordinateDtoMapper coordinatesMapper) {
        this.temperatureProperties = temperatureProperties;
        this.coordinatesMapper = coordinatesMapper;
    }

    @Override
    public Temperature toModel(TemperatureRequest temperatureDto) {
        return Temperature.builder()
                .coordinate(coordinatesMapper.toModel(temperatureDto.getCoordinateRequest()))
                .temperature(scaled(temperatureDto.getTemperature(), temperatureDto.getScale()))
                .time(Instant.now())
                .build();
    }

    @Override
    public TemperatureResponse fromModel(Temperature temperature) {
        return TemperatureResponse.builder()
                .lat(temperature.getCoordinate().getLat())
                .lon(temperature.getCoordinate().getLon())
                .temperature(temperature.getTemperature())
                .time(temperature.getTime())
                .build();
    }

    private double scaled(Double temperature, String scaleString) {
        Scale scale = StringUtils.isEmpty(scaleString) ?
                temperatureProperties.getDefaults().getScale() : Scale.valueOf(scaleString);
        return scale.toCelsius(temperature);
    }
}

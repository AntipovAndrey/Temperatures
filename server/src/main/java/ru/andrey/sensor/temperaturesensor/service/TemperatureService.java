package ru.andrey.sensor.temperaturesensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.model.Scale;
import ru.andrey.sensor.temperaturesensor.model.Temperature;
import ru.andrey.sensor.temperaturesensor.repository.TemperatureRepository;

@Service
public class TemperatureService {

    private final TemperatureProperties temperatureProperties;
    private final TemperatureRepository temperatureRepository;

    @Autowired
    public TemperatureService(TemperatureProperties temperatureProperties,
                              TemperatureRepository temperatureRepository) {
        this.temperatureProperties = temperatureProperties;
        this.temperatureRepository = temperatureRepository;
    }

    public void store(TemperatureRequest temperatureRequest) {
        Temperature temperatureMessage = Temperature.builder()
                .lat(temperatureRequest.getLat().doubleValue())
                .lon(temperatureRequest.getLon().doubleValue())
                .temperature(convertTemperature(temperatureRequest.getTemperature(), temperatureRequest.getScale()))
                .build();

        temperatureRepository.save(temperatureMessage);
    }

    private double convertTemperature(Double temperature, String scaleString) {
        Scale scale = StringUtils.isEmpty(scaleString) ?
                temperatureProperties.getDefaults().getScale() : Scale.valueOf(scaleString);
        return scale.toCelsius(temperature);
    }
}

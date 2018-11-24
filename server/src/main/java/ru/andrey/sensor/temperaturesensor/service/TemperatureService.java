package ru.andrey.sensor.temperaturesensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureResponse;
import ru.andrey.sensor.temperaturesensor.model.Scale;
import ru.andrey.sensor.temperaturesensor.model.Temperature;
import ru.andrey.sensor.temperaturesensor.repository.TemperatureRepository;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

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
                .time(Instant.now())
                .build();

        temperatureRepository.save(temperatureMessage);
    }

    public List<TemperatureResponse> getLatest() {
        return temperatureRepository.findAll(limitSort())
                .stream()
                .map(this::fromTemperature)
                .collect(Collectors.toList());
    }

    private double convertTemperature(Double temperature, String scaleString) {
        Scale scale = StringUtils.isEmpty(scaleString) ?
                temperatureProperties.getDefaults().getScale() : Scale.valueOf(scaleString);
        return scale.toCelsius(temperature);
    }

    private TemperatureResponse fromTemperature(Temperature temperature) {
        return TemperatureResponse.builder()
                .lat(temperature.getLat())
                .lon(temperature.getLon())
                .temperature(temperature.getTemperature())
                .time(temperature.getTime())
                .build();
    }

    private PageRequest limitSort() {
        final Sort sortByTime = new Sort(Sort.Direction.DESC, "time");
        return PageRequest.of(0, temperatureProperties.getMaxRecords(), sortByTime);
    }
}

package ru.andrey.sensor.temperaturesensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
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
    private final LocationService locationService;

    @Autowired
    public TemperatureService(TemperatureProperties temperatureProperties,
                              TemperatureRepository temperatureRepository,
                              LocationService locationService) {
        this.temperatureProperties = temperatureProperties;
        this.temperatureRepository = temperatureRepository;
        this.locationService = locationService;
    }

    public void store(TemperatureRequest temperatureRequest) {
        Temperature temperatureMessage = Temperature.builder()
                .lat(temperatureRequest.getLat().doubleValue())
                .lon(temperatureRequest.getLon().doubleValue())
                .temperature(scaled(temperatureRequest.getTemperature(), temperatureRequest.getScale()))
                .time(Instant.now())
                .city(getCity(temperatureRequest.getLon().doubleValue(), temperatureRequest.getLat().doubleValue()))
                .build();

        temperatureRepository.save(temperatureMessage);
    }

    public List<TemperatureResponse> getLatest() {
        final Page<Temperature> list = temperatureRepository.findAll(limitSort());
        return transformToResponse(list);
    }

    public List<TemperatureResponse> getLatestInCity(double lon, double lat) {
        String city = getCity(lon, lat);
        if (city == null) {
            return getLatest();
        }
        final Page<Temperature> list = temperatureRepository.findByCity(city, limitSort());
        return transformToResponse(list);
    }

    private double scaled(Double temperature, String scaleString) {
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

    private List<TemperatureResponse> transformToResponse(Streamable<Temperature> list) {
        return list.stream()
                .map(this::fromTemperature)
                .collect(Collectors.toList());
    }

    private String getCity(double lon, double lat) {
        return locationService.findCityByCoordinates(lon, lat);
    }

    private PageRequest limitSort() {
        final Sort sortByTime = new Sort(Sort.Direction.DESC, "time");
        return PageRequest.of(0, temperatureProperties.getMaxRecords(), sortByTime);
    }
}

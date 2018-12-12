package ru.andrey.sensor.temperaturesensor.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.util.Streamable;
import org.springframework.stereotype.Service;
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse;
import ru.andrey.sensor.temperaturesensor.model.Coordinate;
import ru.andrey.sensor.temperaturesensor.model.Temperature;
import ru.andrey.sensor.temperaturesensor.repository.TemperatureRepository;
import ru.andrey.sensor.temperaturesensor.service.mapping.TemperatureDtoMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TemperatureService {

    private final TemperatureProperties temperatureProperties;
    private final TemperatureRepository temperatureRepository;
    private final LocationService locationService;
    private final TemperatureDtoMapper mapper;

    @Autowired
    public TemperatureService(TemperatureProperties temperatureProperties,
                              TemperatureRepository temperatureRepository,
                              LocationService locationService,
                              TemperatureDtoMapper mapper) {
        this.temperatureProperties = temperatureProperties;
        this.temperatureRepository = temperatureRepository;
        this.locationService = locationService;
        this.mapper = mapper;
    }

    public void store(TemperatureRequest temperatureRequest) {
        Temperature temperature = mapper.toModel(temperatureRequest);
        temperature.setCity(getCity(temperature.getCoordinate()).orElse(null));
        temperatureRepository.save(temperature);
    }

    public List<TemperatureResponse> getLatest() {
        return transformToResponse(temperatureRepository.findAll(limitSort()));
    }

    public List<TemperatureResponse> getLatestInCity(double lon, double lat) {
        return getCity(new Coordinate(lat, lon))
                .map(city -> temperatureRepository.findByCity(city, limitSort()))
                .map(this::transformToResponse)
                .orElseGet(this::getLatest);
    }

    private List<TemperatureResponse> transformToResponse(Streamable<Temperature> list) {
        return list.stream()
                .map(mapper::fromModel)
                .collect(Collectors.toList());
    }

    private Optional<String> getCity(Coordinate coordinate) {
        return locationService.findCityByCoordinates(coordinate);
    }

    private PageRequest limitSort() {
        final Sort sortByTime = new Sort(Sort.Direction.DESC, "time");
        return PageRequest.of(0, temperatureProperties.getMaxRecords(), sortByTime);
    }
}

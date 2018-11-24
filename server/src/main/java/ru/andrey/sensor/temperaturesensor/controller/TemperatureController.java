package ru.andrey.sensor.temperaturesensor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.service.TemperatureService;

import javax.validation.Valid;

@RestController
@RequestMapping("temperatures")
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void addTemperature(@Valid @RequestBody TemperatureRequest temperature) {
        temperatureService.store(temperature);
    }
}

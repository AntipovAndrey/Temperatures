package ru.andrey.sensor.temperaturesensor.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.hateoas.Resources;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest;
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse;
import ru.andrey.sensor.temperaturesensor.service.TemperatureService;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping(value = "temperatures", produces = MediaTypes.HAL_JSON_VALUE)
public class TemperatureController {

    private final TemperatureService temperatureService;

    @Autowired
    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.CREATED)
    public void addTemperature(@Valid @RequestBody TemperatureRequest temperature) {
        temperatureService.store(temperature);
    }

    @GetMapping
    public Resources<TemperatureResponse> getLastRecords(@RequestParam(value = "lon", required = false) Double lon,
                                                         @RequestParam(value = "lat", required = false) Double lat) {
        List<TemperatureResponse> latest;
        Link self;
        if (lon == null || lat == null) {
            latest = temperatureService.getLatest();
            self = linkTo(methodOn(TemperatureController.class).getLastRecords(lon, lat)).withSelfRel().expand();
        } else {
            latest = temperatureService.getLatestInCity(lon, lat);
            self = linkTo(methodOn(TemperatureController.class).getLastRecords(lon, lat)).withSelfRel();
        }

        Resources<TemperatureResponse> latestResources = new Resources<>(latest);
        latestResources.add(self);
        return latestResources;
    }
}

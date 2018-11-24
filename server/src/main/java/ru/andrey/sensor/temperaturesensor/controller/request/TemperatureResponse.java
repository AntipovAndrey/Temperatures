package ru.andrey.sensor.temperaturesensor.controller.request;

import lombok.Builder;
import lombok.Data;

import java.time.Instant;

@Data
@Builder
public class TemperatureResponse {

    private double lat;
    private double lon;
    private double temperature;
    private Instant time;
}

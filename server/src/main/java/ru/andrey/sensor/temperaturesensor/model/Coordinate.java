package ru.andrey.sensor.temperaturesensor.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Coordinate {

    private double lon;
    private double lat;
}

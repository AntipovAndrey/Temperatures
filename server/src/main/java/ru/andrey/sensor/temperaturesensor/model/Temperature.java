package ru.andrey.sensor.temperaturesensor.model;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Builder
@Data
@Document
public class Temperature {

    @Id
    private String id;
    private Coordinate coordinate;
    private double temperature;
    private String city;
    private Instant time;
}

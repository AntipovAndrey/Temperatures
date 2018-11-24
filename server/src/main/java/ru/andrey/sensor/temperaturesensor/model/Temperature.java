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
    private double lat;
    private double lon;
    private double temperature;
    private Instant time;
}

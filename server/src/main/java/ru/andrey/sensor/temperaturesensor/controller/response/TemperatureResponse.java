package ru.andrey.sensor.temperaturesensor.controller.response;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.Instant;

@Data
@Builder
@EqualsAndHashCode(callSuper = true)
@Relation(value = "temperature", collectionRelation = "temperatures")
public class TemperatureResponse extends ResourceSupport {

    private double lat;
    private double lon;
    private double temperature;
    private Instant time;
}

package ru.andrey.sensor.temperaturesensor.controller.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Data
public class TemperatureRequest {

    @JsonUnwrapped
    private CoordinateRequest coordinateRequest;

    @Pattern(regexp = "^(C|F|K|)$")
    private String scale = "";

    @NotNull
    private Double temperature;
}

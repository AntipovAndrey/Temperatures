package ru.andrey.sensor.temperaturesensor.controller.request;

import lombok.Data;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.math.BigDecimal;

@Data
public class TemperatureRequest {

    @NotNull
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private BigDecimal lon;

    @NotNull
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private BigDecimal lat;

    @Pattern(regexp = "^(C|F|K|)$")
    private String scale = "";

    @NotNull
    private Double temperature;
}

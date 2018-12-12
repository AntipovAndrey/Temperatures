package ru.andrey.sensor.temperaturesensor.controller.request

import java.math.BigDecimal
import javax.validation.constraints.DecimalMax
import javax.validation.constraints.DecimalMin
import javax.validation.constraints.NotNull

data class CoordinateRequest(
        @NotNull
        @field:DecimalMin("-90.0")
        @field:DecimalMax("90.0")
        val lat: BigDecimal,

        @NotNull
        @field:DecimalMin("-180.0")
        @field:DecimalMax("180.0")
        val lon: BigDecimal
)

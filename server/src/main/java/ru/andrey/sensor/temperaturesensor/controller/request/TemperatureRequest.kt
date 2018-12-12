package ru.andrey.sensor.temperaturesensor.controller.request

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonUnwrapped
import javax.validation.constraints.NotNull
import javax.validation.constraints.Pattern

data class TemperatureRequest(
        @JsonIgnore
        var coordinateRequest: CoordinateRequest?,

        @field:Pattern(regexp = "^(C|F|K|)$")
        var scale: String = "",

        @field:NotNull
        var temperature: Double
) {
    @get:JsonUnwrapped
    var jacksonWorkaround: CoordinateRequest? // https://github.com/FasterXML/jackson-module-kotlin/issues/50
        get() = coordinateRequest
        set(value) {
            coordinateRequest = value
        }
}

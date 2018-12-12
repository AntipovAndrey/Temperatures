package ru.andrey.sensor.temperaturesensor.controller.response

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.core.Relation
import java.time.Instant

@Relation(value = "temperature", collectionRelation = "temperatures")
data class TemperatureResponse(
        var lat: Double,
        var lon: Double,
        var temperature: Double,
        var time: Instant
) : ResourceSupport()

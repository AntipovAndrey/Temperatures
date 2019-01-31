package ru.andrey.sensor.temperaturesensor.controller.response

import org.springframework.hateoas.ResourceSupport
import org.springframework.hateoas.core.Relation
import java.time.Instant

@Relation(value = "temperature", collectionRelation = "temperatures")
data class TemperatureResponse(
        val lat: Double,
        val lon: Double,
        val temperature: Double,
        val time: Instant
) : ResourceSupport()

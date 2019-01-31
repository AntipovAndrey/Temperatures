package ru.andrey.sensor.temperaturesensor.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.Instant

@Document
data class Temperature(
        val coordinate: Coordinate,
        val temperature: Double,
        val time: Instant
) {

    @field:Id
    var id: String? = null
    var city: String? = null
}

package ru.andrey.sensor.temperaturesensor.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

import java.time.Instant

@Document
data class Temperature(
        @field:Id
        val id: String? = null,
        val coordinate: Coordinate,
        val temperature: Double,
        var city: String? = null,
        val time: Instant
)

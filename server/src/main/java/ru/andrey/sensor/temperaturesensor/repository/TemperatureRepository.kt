package ru.andrey.sensor.temperaturesensor.repository

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.mongodb.repository.MongoRepository
import ru.andrey.sensor.temperaturesensor.model.Temperature

interface TemperatureRepository : MongoRepository<Temperature, String> {

    fun findByCity(city: String, limitSort: PageRequest): Page<Temperature>
}

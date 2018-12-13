package ru.andrey.sensor.temperaturesensor.service

import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse
import ru.andrey.sensor.temperaturesensor.model.Coordinate
import ru.andrey.sensor.temperaturesensor.model.Temperature
import ru.andrey.sensor.temperaturesensor.repository.TemperatureRepository
import ru.andrey.sensor.temperaturesensor.service.mapping.TemperatureDtoMapper

@Service
class TemperatureService(private val temperatureProperties: TemperatureProperties,
                         private val temperatureRepository: TemperatureRepository,
                         private val locationService: LocationService,
                         private val mapper: TemperatureDtoMapper) {

    fun latest() = transformToResponse(temperatureRepository.findAll(limitSort()))

    fun store(temperatureRequest: TemperatureRequest) {
        val temperature = mapper.toModel(temperatureRequest)
        temperature.city = getCity(temperature.coordinate)
        temperatureRepository.save(temperature)
    }

    fun latestInCity(lon: Double, lat: Double): List<TemperatureResponse> {
        return getCity(Coordinate(lat = lat, lon = lon))
                ?.let { city ->
                    transformToResponse(temperatureRepository.findByCity(city, limitSort()))
                } ?: latest();
    }

    private fun transformToResponse(list: Iterable<Temperature>) = list.map { mapper.fromModel(it) }.toList();

    private fun getCity(coordinate: Coordinate): String? = locationService.findCityByCoordinates(coordinate)

    private fun limitSort(): PageRequest {
        val sortByTime = Sort(Sort.Direction.DESC, "time")
        return PageRequest.of(0, temperatureProperties.maxRecords, sortByTime)
    }
}

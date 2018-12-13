package ru.andrey.sensor.temperaturesensor.service

import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import ru.andrey.sensor.temperaturesensor.config.GeoLocationProperties
import ru.andrey.sensor.temperaturesensor.model.Coordinate

@Service
class LocationService(val geoProps: GeoLocationProperties) {

    fun findCityByCoordinates(coordinate: Coordinate): String? {
        val restTemplate = RestTemplate()
        return extractCity(restTemplate.getForObject(formUrl(coordinate)))
    }

    private fun extractCity(json: String?): String? {
        return json?.let {
            ObjectMapper().readTree(it)
                    ?.run { get("results") }
                    ?.run { get(0) }
                    ?.run { get("components") }
                    ?.run { get("city") }
                    ?.run { asText() }
        }
    }

    private fun formUrl(coordinate: Coordinate): String {
        return "${geoProps.baseUrl}?q=${coordinate.lat},${coordinate.lon}&key=${geoProps.key}&language=en"
    }
}

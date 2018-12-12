package ru.andrey.sensor.temperaturesensor.service.mapping

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import org.springframework.util.StringUtils
import ru.andrey.sensor.temperaturesensor.config.TemperatureProperties
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse
import ru.andrey.sensor.temperaturesensor.model.Scale
import ru.andrey.sensor.temperaturesensor.model.Temperature

import java.time.Instant

@Component
class TemperatureDtoMapper @Autowired constructor(
        private val temperatureProperties: TemperatureProperties,
        private val coordinatesMapper: CoordinateDtoMapper)
    : DuplexMapper<TemperatureRequest, TemperatureResponse, Temperature> {

    override fun toModel(dto: TemperatureRequest) =
            Temperature(
                    coordinate = coordinatesMapper.toModel(dto.coordinateRequest!!),
                    temperature = scaled(dto.temperature, dto.scale),
                    time = Instant.now())

    override fun fromModel(model: Temperature) =
            TemperatureResponse(
                    lat = model.coordinate.lat,
                    lon = model.coordinate.lon,
                    temperature = model.temperature,
                    time = model.time)


    private fun scaled(temperature: Double, scaleString: String): Double {
        val scale = if (StringUtils.isEmpty(scaleString))
            temperatureProperties.defaults.scale
        else
            Scale.valueOf(scaleString)
        return scale.toCelsius(temperature)
    }
}

package ru.andrey.sensor.temperaturesensor.service.mapping

import org.springframework.stereotype.Component
import ru.andrey.sensor.temperaturesensor.controller.request.CoordinateRequest
import ru.andrey.sensor.temperaturesensor.model.Coordinate

@Component
class CoordinateDtoMapper : SimplexMapper<CoordinateRequest, Coordinate> {

    override fun toModel(dto: CoordinateRequest) = Coordinate(dto.lat.toDouble(), dto.lon.toDouble())
}

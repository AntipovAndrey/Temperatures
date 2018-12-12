package ru.andrey.sensor.temperaturesensor.service.mapping;

import org.springframework.stereotype.Component;
import ru.andrey.sensor.temperaturesensor.controller.request.CoordinateRequest;
import ru.andrey.sensor.temperaturesensor.model.Coordinate;

@Component()
public class CoordinateDtoMapper implements SimplexMapper<CoordinateRequest, Coordinate> {

    @Override
    public Coordinate toModel(CoordinateRequest dto) {
        return new Coordinate(dto.getLat().doubleValue(), dto.getLon().doubleValue());
    }
}

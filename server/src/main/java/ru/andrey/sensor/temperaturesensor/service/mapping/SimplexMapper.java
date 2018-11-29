package ru.andrey.sensor.temperaturesensor.service.mapping;

public interface SimplexMapper<REQ, M> {

    M toModel(REQ dto);
}

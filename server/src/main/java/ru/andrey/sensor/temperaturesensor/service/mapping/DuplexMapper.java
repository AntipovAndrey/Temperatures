package ru.andrey.sensor.temperaturesensor.service.mapping;

public interface DuplexMapper<REQ, RES, M> extends SimplexMapper<REQ, M> {

    RES fromModel(M model);
}

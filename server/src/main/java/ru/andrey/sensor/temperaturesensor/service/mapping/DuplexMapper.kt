package ru.andrey.sensor.temperaturesensor.service.mapping

interface DuplexMapper<REQ, RES, M> : SimplexMapper<REQ, M> {

    fun fromModel(model: M): RES
}

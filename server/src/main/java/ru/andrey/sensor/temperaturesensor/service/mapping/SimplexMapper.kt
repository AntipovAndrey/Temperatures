package ru.andrey.sensor.temperaturesensor.service.mapping

interface SimplexMapper<REQ, M> {

    fun toModel(dto: REQ): M
}

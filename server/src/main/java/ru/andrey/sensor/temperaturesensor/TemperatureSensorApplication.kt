package ru.andrey.sensor.temperaturesensor

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import java.util.*
import javax.annotation.PostConstruct

@SpringBootApplication
class TemperatureSensorApplication {

    @PostConstruct
    private fun init() {
        TimeZone.setDefault(TimeZone.getTimeZone("UTC"))
    }
}

fun main(args: Array<String>) {
    runApplication<TemperatureSensorApplication>(*args)
}

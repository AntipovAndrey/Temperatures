package ru.andrey.sensor.temperaturesensor.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration
import ru.andrey.sensor.temperaturesensor.model.Scale

@Configuration
@ConfigurationProperties(prefix = "temperature")
class TemperatureProperties {

    var maxRecords: Int = 0

    val defaults = Defaults()

    open class Defaults {
        open lateinit var scale: Scale
    }
}

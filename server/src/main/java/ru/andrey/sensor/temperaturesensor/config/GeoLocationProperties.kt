package ru.andrey.sensor.temperaturesensor.config

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.context.annotation.Configuration

@Configuration
@ConfigurationProperties(prefix = "opencage")
class GeoLocationProperties {

    lateinit var baseUrl: String
    lateinit var key: String
}

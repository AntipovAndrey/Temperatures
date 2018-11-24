package ru.andrey.sensor.temperaturesensor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "opencage")
@Getter
@Setter
public class GeoLocationProperties {

    private String baseUrl;
    private String key;
}

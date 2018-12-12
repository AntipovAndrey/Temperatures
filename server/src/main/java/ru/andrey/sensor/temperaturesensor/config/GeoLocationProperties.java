package ru.andrey.sensor.temperaturesensor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "opencage")
public class GeoLocationProperties {

    private String baseUrl;
    private String key;

    public String getBaseUrl() {
        return this.baseUrl;
    }

    public String getKey() {
        return this.key;
    }

    public void setBaseUrl(String baseUrl) {
        this.baseUrl = baseUrl;
    }

    public void setKey(String key) {
        this.key = key;
    }
}

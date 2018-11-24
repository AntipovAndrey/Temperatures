package ru.andrey.sensor.temperaturesensor.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.andrey.sensor.temperaturesensor.model.Scale;

@Configuration
@ConfigurationProperties(prefix = "temperature")
@Getter
@Setter
public class TemperatureProperties {

    private int maxRecords;

    private final Defaults defaults = new Defaults();

    @Getter
    @Setter
    public static class Defaults {
        private Scale scale;
    }
}

package ru.andrey.sensor.temperaturesensor.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import ru.andrey.sensor.temperaturesensor.model.Scale;

@Configuration
@ConfigurationProperties(prefix = "temperature")
public class TemperatureProperties {

    private int maxRecords;

    private final Defaults defaults = new Defaults();

    public int getMaxRecords() {
        return this.maxRecords;
    }

    public Defaults getDefaults() {
        return this.defaults;
    }

    public void setMaxRecords(int maxRecords) {
        this.maxRecords = maxRecords;
    }

    public static class Defaults {
        private Scale scale;

        public Scale getScale() {
            return this.scale;
        }

        public void setScale(Scale scale) {
            this.scale = scale;
        }
    }
}

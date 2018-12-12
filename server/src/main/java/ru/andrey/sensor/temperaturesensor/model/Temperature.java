package ru.andrey.sensor.temperaturesensor.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document
public class Temperature {

    @Id
    private String id;
    private Coordinate coordinate;
    private double temperature;
    private String city;
    private Instant time;

    @java.beans.ConstructorProperties({"id", "coordinate", "temperature", "city", "time"})
    Temperature(String id, Coordinate coordinate, double temperature, String city, Instant time) {
        this.id = id;
        this.coordinate = coordinate;
        this.temperature = temperature;
        this.city = city;
        this.time = time;
    }

    public static TemperatureBuilder builder() {
        return new TemperatureBuilder();
    }

    public String getId() {
        return this.id;
    }

    public Coordinate getCoordinate() {
        return this.coordinate;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public String getCity() {
        return this.city;
    }

    public Instant getTime() {
        return this.time;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setCoordinate(Coordinate coordinate) {
        this.coordinate = coordinate;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Temperature)) return false;
        final Temperature other = (Temperature) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$id = this.getId();
        final Object other$id = other.getId();
        if (this$id == null ? other$id != null : !this$id.equals(other$id)) return false;
        final Object this$coordinate = this.getCoordinate();
        final Object other$coordinate = other.getCoordinate();
        if (this$coordinate == null ? other$coordinate != null : !this$coordinate.equals(other$coordinate))
            return false;
        if (Double.compare(this.getTemperature(), other.getTemperature()) != 0) return false;
        final Object this$city = this.getCity();
        final Object other$city = other.getCity();
        if (this$city == null ? other$city != null : !this$city.equals(other$city)) return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $id = this.getId();
        result = result * PRIME + ($id == null ? 43 : $id.hashCode());
        final Object $coordinate = this.getCoordinate();
        result = result * PRIME + ($coordinate == null ? 43 : $coordinate.hashCode());
        final long $temperature = Double.doubleToLongBits(this.getTemperature());
        result = result * PRIME + (int) ($temperature >>> 32 ^ $temperature);
        final Object $city = this.getCity();
        result = result * PRIME + ($city == null ? 43 : $city.hashCode());
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Temperature;
    }

    public String toString() {
        return "Temperature(id=" + this.getId() + ", coordinate=" + this.getCoordinate() + ", temperature=" + this.getTemperature() + ", city=" + this.getCity() + ", time=" + this.getTime() + ")";
    }

    public static class TemperatureBuilder {
        private String id;
        private Coordinate coordinate;
        private double temperature;
        private String city;
        private Instant time;

        TemperatureBuilder() {
        }

        public Temperature.TemperatureBuilder id(String id) {
            this.id = id;
            return this;
        }

        public Temperature.TemperatureBuilder coordinate(Coordinate coordinate) {
            this.coordinate = coordinate;
            return this;
        }

        public Temperature.TemperatureBuilder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public Temperature.TemperatureBuilder city(String city) {
            this.city = city;
            return this;
        }

        public Temperature.TemperatureBuilder time(Instant time) {
            this.time = time;
            return this;
        }

        public Temperature build() {
            return new Temperature(id, coordinate, temperature, city, time);
        }

        public String toString() {
            return "Temperature.TemperatureBuilder(id=" + this.id + ", coordinate=" + this.coordinate + ", temperature=" + this.temperature + ", city=" + this.city + ", time=" + this.time + ")";
        }
    }
}

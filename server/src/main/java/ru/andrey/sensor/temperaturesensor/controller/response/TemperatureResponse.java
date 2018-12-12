package ru.andrey.sensor.temperaturesensor.controller.response;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.hateoas.core.Relation;

import java.time.Instant;

@Relation(value = "temperature", collectionRelation = "temperatures")
public class TemperatureResponse extends ResourceSupport {

    private double lat;
    private double lon;
    private double temperature;
    private Instant time;

    @java.beans.ConstructorProperties({"lat", "lon", "temperature", "time"})
    TemperatureResponse(double lat, double lon, double temperature, Instant time) {
        this.lat = lat;
        this.lon = lon;
        this.temperature = temperature;
        this.time = time;
    }

    public static TemperatureResponseBuilder builder() {
        return new TemperatureResponseBuilder();
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public double getTemperature() {
        return this.temperature;
    }

    public Instant getTime() {
        return this.time;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setTime(Instant time) {
        this.time = time;
    }

    public String toString() {
        return "TemperatureResponse(lat=" + this.getLat() + ", lon=" + this.getLon() + ", temperature=" + this.getTemperature() + ", time=" + this.getTime() + ")";
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TemperatureResponse)) return false;
        final TemperatureResponse other = (TemperatureResponse) o;
        if (!other.canEqual((Object) this)) return false;
        if (!super.equals(o)) return false;
        if (Double.compare(this.getLat(), other.getLat()) != 0) return false;
        if (Double.compare(this.getLon(), other.getLon()) != 0) return false;
        if (Double.compare(this.getTemperature(), other.getTemperature()) != 0) return false;
        final Object this$time = this.getTime();
        final Object other$time = other.getTime();
        if (this$time == null ? other$time != null : !this$time.equals(other$time)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        result = result * PRIME + super.hashCode();
        final long $lat = Double.doubleToLongBits(this.getLat());
        result = result * PRIME + (int) ($lat >>> 32 ^ $lat);
        final long $lon = Double.doubleToLongBits(this.getLon());
        result = result * PRIME + (int) ($lon >>> 32 ^ $lon);
        final long $temperature = Double.doubleToLongBits(this.getTemperature());
        result = result * PRIME + (int) ($temperature >>> 32 ^ $temperature);
        final Object $time = this.getTime();
        result = result * PRIME + ($time == null ? 43 : $time.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof TemperatureResponse;
    }

    public static class TemperatureResponseBuilder {
        private double lat;
        private double lon;
        private double temperature;
        private Instant time;

        TemperatureResponseBuilder() {
        }

        public TemperatureResponse.TemperatureResponseBuilder lat(double lat) {
            this.lat = lat;
            return this;
        }

        public TemperatureResponse.TemperatureResponseBuilder lon(double lon) {
            this.lon = lon;
            return this;
        }

        public TemperatureResponse.TemperatureResponseBuilder temperature(double temperature) {
            this.temperature = temperature;
            return this;
        }

        public TemperatureResponse.TemperatureResponseBuilder time(Instant time) {
            this.time = time;
            return this;
        }

        public TemperatureResponse build() {
            return new TemperatureResponse(lat, lon, temperature, time);
        }

        public String toString() {
            return "TemperatureResponse.TemperatureResponseBuilder(lat=" + this.lat + ", lon=" + this.lon + ", temperature=" + this.temperature + ", time=" + this.time + ")";
        }
    }
}

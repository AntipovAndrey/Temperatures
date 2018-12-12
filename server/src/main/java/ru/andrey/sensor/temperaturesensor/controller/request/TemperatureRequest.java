package ru.andrey.sensor.temperaturesensor.controller.request;

import com.fasterxml.jackson.annotation.JsonUnwrapped;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

public class TemperatureRequest {

    @JsonUnwrapped
    private CoordinateRequest coordinateRequest;

    @Pattern(regexp = "^(C|F|K|)$")
    private String scale = "";

    @NotNull
    private Double temperature;

    public TemperatureRequest() {
    }

    public CoordinateRequest getCoordinateRequest() {
        return this.coordinateRequest;
    }

    public @Pattern(regexp = "^(C|F|K|)$") String getScale() {
        return this.scale;
    }

    public @NotNull Double getTemperature() {
        return this.temperature;
    }

    public void setCoordinateRequest(CoordinateRequest coordinateRequest) {
        this.coordinateRequest = coordinateRequest;
    }

    public void setScale(@Pattern(regexp = "^(C|F|K|)$") String scale) {
        this.scale = scale;
    }

    public void setTemperature(@NotNull Double temperature) {
        this.temperature = temperature;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof TemperatureRequest)) return false;
        final TemperatureRequest other = (TemperatureRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$coordinateRequest = this.getCoordinateRequest();
        final Object other$coordinateRequest = other.getCoordinateRequest();
        if (this$coordinateRequest == null ? other$coordinateRequest != null : !this$coordinateRequest.equals(other$coordinateRequest))
            return false;
        final Object this$scale = this.getScale();
        final Object other$scale = other.getScale();
        if (this$scale == null ? other$scale != null : !this$scale.equals(other$scale)) return false;
        final Object this$temperature = this.getTemperature();
        final Object other$temperature = other.getTemperature();
        if (this$temperature == null ? other$temperature != null : !this$temperature.equals(other$temperature))
            return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $coordinateRequest = this.getCoordinateRequest();
        result = result * PRIME + ($coordinateRequest == null ? 43 : $coordinateRequest.hashCode());
        final Object $scale = this.getScale();
        result = result * PRIME + ($scale == null ? 43 : $scale.hashCode());
        final Object $temperature = this.getTemperature();
        result = result * PRIME + ($temperature == null ? 43 : $temperature.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof TemperatureRequest;
    }

    public String toString() {
        return "TemperatureRequest(coordinateRequest=" + this.getCoordinateRequest() + ", scale=" + this.getScale() + ", temperature=" + this.getTemperature() + ")";
    }
}

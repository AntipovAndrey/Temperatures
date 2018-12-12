package ru.andrey.sensor.temperaturesensor.controller.request;

import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class CoordinateRequest {

    @NotNull
    @DecimalMin("-180.0")
    @DecimalMax("180.0")
    private BigDecimal lon;

    @NotNull
    @DecimalMin("-90.0")
    @DecimalMax("90.0")
    private BigDecimal lat;

    public CoordinateRequest() {
    }

    public @NotNull @DecimalMin("-180.0") @DecimalMax("180.0") BigDecimal getLon() {
        return this.lon;
    }

    public @NotNull @DecimalMin("-90.0") @DecimalMax("90.0") BigDecimal getLat() {
        return this.lat;
    }

    public void setLon(@NotNull @DecimalMin("-180.0") @DecimalMax("180.0") BigDecimal lon) {
        this.lon = lon;
    }

    public void setLat(@NotNull @DecimalMin("-90.0") @DecimalMax("90.0") BigDecimal lat) {
        this.lat = lat;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof CoordinateRequest)) return false;
        final CoordinateRequest other = (CoordinateRequest) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$lon = this.getLon();
        final Object other$lon = other.getLon();
        if (this$lon == null ? other$lon != null : !this$lon.equals(other$lon)) return false;
        final Object this$lat = this.getLat();
        final Object other$lat = other.getLat();
        if (this$lat == null ? other$lat != null : !this$lat.equals(other$lat)) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $lon = this.getLon();
        result = result * PRIME + ($lon == null ? 43 : $lon.hashCode());
        final Object $lat = this.getLat();
        result = result * PRIME + ($lat == null ? 43 : $lat.hashCode());
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof CoordinateRequest;
    }

    public String toString() {
        return "CoordinateRequest(lon=" + this.getLon() + ", lat=" + this.getLat() + ")";
    }
}

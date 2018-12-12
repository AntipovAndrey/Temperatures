package ru.andrey.sensor.temperaturesensor.model;

public class Coordinate {

    private double lon;
    private double lat;

    @java.beans.ConstructorProperties({"lon", "lat"})
    Coordinate(double lon, double lat) {
        this.lon = lon;
        this.lat = lat;
    }

    public static CoordinateBuilder builder() {
        return new CoordinateBuilder();
    }

    public double getLon() {
        return this.lon;
    }

    public double getLat() {
        return this.lat;
    }

    public void setLon(double lon) {
        this.lon = lon;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public boolean equals(Object o) {
        if (o == this) return true;
        if (!(o instanceof Coordinate)) return false;
        final Coordinate other = (Coordinate) o;
        if (!other.canEqual((Object) this)) return false;
        if (Double.compare(this.getLon(), other.getLon()) != 0) return false;
        if (Double.compare(this.getLat(), other.getLat()) != 0) return false;
        return true;
    }

    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final long $lon = Double.doubleToLongBits(this.getLon());
        result = result * PRIME + (int) ($lon >>> 32 ^ $lon);
        final long $lat = Double.doubleToLongBits(this.getLat());
        result = result * PRIME + (int) ($lat >>> 32 ^ $lat);
        return result;
    }

    protected boolean canEqual(Object other) {
        return other instanceof Coordinate;
    }

    public String toString() {
        return "Coordinate(lon=" + this.getLon() + ", lat=" + this.getLat() + ")";
    }

    public static class CoordinateBuilder {
        private double lon;
        private double lat;

        CoordinateBuilder() {
        }

        public Coordinate.CoordinateBuilder lon(double lon) {
            this.lon = lon;
            return this;
        }

        public Coordinate.CoordinateBuilder lat(double lat) {
            this.lat = lat;
            return this;
        }

        public Coordinate build() {
            return new Coordinate(lon, lat);
        }

        public String toString() {
            return "Coordinate.CoordinateBuilder(lon=" + this.lon + ", lat=" + this.lat + ")";
        }
    }
}

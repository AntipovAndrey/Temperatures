package ru.andrey.sensor.temperaturesensor.model;

public enum Scale {
    F {
        @Override
        public double toCelsius(double fahrenheit) {
            return (fahrenheit - 32) * (5.0 / 9.0);
        }
    }, C {
        @Override
        public double toCelsius(double celcius) {
            return celcius;
        }
    }, K {
        @Override
        public double toCelsius(double kelvin) {
            return kelvin - 273.15;
        }
    };

    public abstract double toCelsius(double temperature);
}


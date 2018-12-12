package ru.andrey.sensor.temperaturesensor.model

enum class Scale {
    F {
        override fun toCelsius(temperature: Double): Double {
            return (temperature - 32) * (5.0 / 9.0)
        }
    },
    C {
        override fun toCelsius(temperature: Double): Double {
            return temperature
        }
    },
    K {
        override fun toCelsius(temperature: Double): Double {
            return temperature - 273.15
        }
    };

    abstract fun toCelsius(temperature: Double): Double
}


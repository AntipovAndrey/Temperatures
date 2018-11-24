package ru.andrey.sensor.temperaturesensor.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import ru.andrey.sensor.temperaturesensor.model.Temperature;

public interface TemperatureRepository extends MongoRepository<Temperature, String> {

}

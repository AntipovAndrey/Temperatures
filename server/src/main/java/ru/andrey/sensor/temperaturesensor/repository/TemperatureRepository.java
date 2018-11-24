package ru.andrey.sensor.temperaturesensor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;
import ru.andrey.sensor.temperaturesensor.model.Temperature;

public interface TemperatureRepository extends MongoRepository<Temperature, String> {

    Page<Temperature> findByCity(String city, PageRequest limitSort);
}

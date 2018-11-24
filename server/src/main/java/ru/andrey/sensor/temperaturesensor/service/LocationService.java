package ru.andrey.sensor.temperaturesensor.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.andrey.sensor.temperaturesensor.config.GeoLocationProperties;

import java.io.IOException;
import java.util.Optional;

@Service
public class LocationService {

    private final GeoLocationProperties geoServiceProperties;

    @Autowired
    public LocationService(GeoLocationProperties geoServiceProperties) {
        this.geoServiceProperties = geoServiceProperties;
    }

    public String findCityByCoordinates(double lon, double lat) {
        RestTemplate restTemplate = new RestTemplate();
        String json = restTemplate.getForObject(formUrl(lon, lat), String.class);
        return extractCity(json).orElse(null);
    }

    private Optional<String> extractCity(String json) {
        try {
            JsonNode tree = new ObjectMapper()
                    .readTree(json);

            return Optional.of(tree)
                    .map(n -> n.get("results"))
                    .map(n -> n.get(0))
                    .map(n -> n.get("components"))
                    .map(n -> n.get("city"))
                    .map(JsonNode::asText);

        } catch (IOException e) {
            return Optional.empty();
        }
    }

    private String formUrl(double lon, double lat) {
        return String.format("%s?q=%s,%s&key=%s&language=en",
                geoServiceProperties.getBaseUrl(),
                lat,
                lon,
                geoServiceProperties.getKey());
    }
}

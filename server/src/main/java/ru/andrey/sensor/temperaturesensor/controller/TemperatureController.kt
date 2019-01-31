package ru.andrey.sensor.temperaturesensor.controller

import org.springframework.hateoas.Link
import org.springframework.hateoas.MediaTypes
import org.springframework.hateoas.Resources
import org.springframework.hateoas.core.DummyInvocationUtils.methodOn
import org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import ru.andrey.sensor.temperaturesensor.controller.request.TemperatureRequest
import ru.andrey.sensor.temperaturesensor.controller.response.TemperatureResponse
import ru.andrey.sensor.temperaturesensor.service.TemperatureService
import javax.validation.Valid

@RestController
@RequestMapping(value = ["temperatures"], produces = [MediaTypes.HAL_JSON_VALUE])
class TemperatureController(
        private val temperatureService: TemperatureService
) {

    @PostMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseStatus(HttpStatus.CREATED)
    fun addTemperature(@Valid @RequestBody temperature: TemperatureRequest) = temperatureService.store(temperature)

    @GetMapping
    fun getLastRecords(
            @RequestParam(value = "lon", required = false) lon: Double?,
            @RequestParam(value = "lat", required = false) lat: Double?
    ): Resources<TemperatureResponse> {
        val latest: List<TemperatureResponse>
        val self: Link

        if (lat == null || lon == null) {
            latest = temperatureService.latest()
            self = linkTo(methodOn(TemperatureController::class.java).getLastRecords(lon, lat)).withSelfRel().expand()
        } else {
            latest = temperatureService.latestInCity(lon, lat)
            self = linkTo(methodOn(TemperatureController::class.java).getLastRecords(lon, lat)).withSelfRel()
        }

        return Resources(latest).apply { add(self) }
    }
}

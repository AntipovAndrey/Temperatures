package ru.andrey.sensor.temperaturesensor.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler

@ControllerAdvice
class RestResponseEntityExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(ex: MethodArgumentNotValidException,
                                              headers: HttpHeaders,
                                              status: HttpStatus,
                                              request: WebRequest): ResponseEntity<Any> {
        val fieldErrors = ex.bindingResult.fieldErrors
        val errorsMap = propertyToErrorMessage(fieldErrors)
        return ResponseEntity(if (errorsMap.isEmpty()) ex else errorsMap, headers, status)
    }

    private fun propertyToErrorMessage(fieldErrors: List<FieldError>): Map<String, List<String>> {

        return fieldErrors.groupBy({ filterJacksonUnwrapped(it) }, { it.defaultMessage!! })
    }

    private fun filterJacksonUnwrapped(fieldError: FieldError): String {
        val error = fieldError.field
        if (error.contains("jacksonUnwrapped")) {
            val split = error.split("\\.".toRegex()).dropLastWhile { it.isEmpty() }
            return if (split.size < 2) {
                error
            } else split[1]
        }
        return error
    }

}

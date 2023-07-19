package io.github.kji6252.adapter.web.input

import io.github.kji6252.port.input.service.ServiceUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val serviceUseCase: ServiceUseCase,
) {

    @GetMapping("/services/{serviceId}")
    fun getService(@PathVariable serviceId: String): ServiceResponse {
        return serviceUseCase.getService(serviceId)
            .let { ServiceResponse(name = it.name) }
    }
}


data class ServiceResponse(
    val name: String,
)
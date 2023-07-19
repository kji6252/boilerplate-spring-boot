package io.github.kji6252.application.service

import io.github.kji6252.port.input.service.ServiceUseCase
import io.github.kji6252.port.output.service.LoadServicePort
import org.springframework.stereotype.Service

@Service
class GeneralService(
    private val loadServicePort: LoadServicePort,
) : ServiceUseCase {
    override fun getService(serviceId: String): io.github.kji6252.domain.entity.Service {
        return loadServicePort.load(serviceId)
    }

}
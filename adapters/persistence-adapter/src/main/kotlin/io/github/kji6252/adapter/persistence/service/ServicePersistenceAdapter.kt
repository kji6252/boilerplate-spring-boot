package io.github.kji6252.adapter.persistence.service

import io.github.kji6252.adapter.persistence.service.jpa.repository.ServiceJpaRepository
import io.github.kji6252.domain.entity.Service
import io.github.kji6252.port.output.service.LoadServicePort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class ServicePersistenceAdapter(
    private val serviceJpaRepository: ServiceJpaRepository,
) : LoadServicePort {

    override fun load(serviceId: String): Service = serviceJpaRepository.findByIdOrNull(serviceId)?.let { Service(name = it.serviceName) } ?: throw IllegalArgumentException("${serviceId}는 존재 하지 않습니다.")
}
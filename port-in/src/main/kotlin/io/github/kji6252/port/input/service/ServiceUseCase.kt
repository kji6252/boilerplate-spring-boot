package io.github.kji6252.port.input.service

import io.github.kji6252.domain.entity.Service

interface ServiceUseCase {
    fun getService(serviceId: String): Service
}
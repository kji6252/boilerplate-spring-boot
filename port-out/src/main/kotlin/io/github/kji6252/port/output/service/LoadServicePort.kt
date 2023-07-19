package io.github.kji6252.port.output.service

import io.github.kji6252.domain.entity.Service

interface LoadServicePort {
    fun load(serviceId: String): Service
}
package io.github.kji6252.adapter.persistence.service

import io.github.kji6252.adapter.persistence.service.jpa.entity.ServiceJpaEntity
import io.github.kji6252.adapter.persistence.service.jpa.repository.ServiceJpaRepository
import io.kotest.matchers.nulls.shouldNotBeNull
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Tag
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.data.repository.findByIdOrNull

@Tag("integration")
@DataJpaTest(showSql = true)
class ServicePersistenceAdapterTest @Autowired constructor(
    private val serviceJpaRepository: ServiceJpaRepository,
) {

    @Test
    fun load() {
        val serviceJpaEntity: ServiceJpaEntity? = serviceJpaRepository.findByIdOrNull("1")
        println(serviceJpaEntity)
        serviceJpaEntity.shouldNotBeNull()
    }
}
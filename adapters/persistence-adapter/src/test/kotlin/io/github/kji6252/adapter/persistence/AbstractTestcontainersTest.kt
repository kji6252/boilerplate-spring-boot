package io.github.kji6252.adapter.persistence

import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
abstract class AbstractTestcontainersTest {

    companion object {
        @JvmStatic
        @Container
        @ServiceConnection
        var postgreSQLContainer = PostgreSQLContainer("postgres:latest")
    }
}
package io.github.kji6252.sampleeapp

import org.junit.jupiter.api.Assertions.*
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.boot.testcontainers.service.connection.ServiceConnection
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Import
import org.testcontainers.containers.PostgreSQLContainer

@Import(SampleApp::class)
@SpringBootApplication
@TestConfiguration(proxyBeanMethods = false)
class TestApiApplication {

    @Bean
    @ServiceConnection
//  @RestartScope
    fun postgresContainer(): PostgreSQLContainer<*> {
        return PostgreSQLContainer("postgres:latest")
    }

//  @Bean
//  @ServiceConnection
//  @RestartScope
//  fun kafkaContainer(): KafkaContainer = KafkaContainer(DockerImageName.parse("confluentinc/cp-kafka:7.4.0"))
//
//  @Bean
//  @RestartScope
//  fun localStackContainer(): LocalStackContainer =
//    LocalStackContainer(DockerImageName.parse("localstack/localstack:2.1.0"))
//      .withServices(LocalStackContainer.Service.SQS)

}

fun main(args: Array<String>) {
    runApplication<TestApiApplication>(*args)
}

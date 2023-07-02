package io.github.kji6252.application

import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.scheduling.annotation.EnableAsync

@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
@Import(ApplicationConfig::class)
annotation class EnableApplication

@ComponentScan(
    basePackageClasses = [ApplicationConfig::class],
)
@EnableAsync
@Configuration
class ApplicationConfig

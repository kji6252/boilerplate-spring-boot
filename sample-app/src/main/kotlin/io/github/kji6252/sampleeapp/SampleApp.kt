package io.github.kji6252.sampleeapp

import io.github.kji6252.adapter.persistence.EnablePersistenceAdapter
import io.github.kji6252.adapter.web.EnableWebAdapter
import io.github.kji6252.application.EnableApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@EnableApplication
@EnableWebAdapter
@EnablePersistenceAdapter
@SpringBootApplication
class SampleApp

fun main(args: Array<String>) {
    runApplication<SampleApp>(*args)
}

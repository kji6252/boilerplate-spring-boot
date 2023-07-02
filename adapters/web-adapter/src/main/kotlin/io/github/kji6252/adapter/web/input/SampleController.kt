package io.github.kji6252.adapter.web.input

import io.github.kji6252.port.input.HelloUseCase
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class SampleController(
    private val helloUseCase: HelloUseCase,
) {

    @GetMapping("/hello")
    fun hello(): String {
        return helloUseCase.hello()
    }
}
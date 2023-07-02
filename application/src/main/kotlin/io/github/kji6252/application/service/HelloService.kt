package io.github.kji6252.application.service

import io.github.kji6252.port.input.HelloUseCase
import org.springframework.stereotype.Service

@Service
class HelloService : HelloUseCase {
    override fun hello(): String {
        return "hello"
    }
}
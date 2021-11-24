package ca.rmen.poetassistant

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
@RestController
class PoetAssistantApplication {
}

fun main(args: Array<String>) {
	runApplication<PoetAssistantApplication>(*args)
}

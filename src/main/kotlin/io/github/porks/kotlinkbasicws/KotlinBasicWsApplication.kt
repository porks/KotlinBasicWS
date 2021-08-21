package io.github.porks.kotlinkbasicws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KotlinBasicWsApplication

data class Message(val id: String?, val text: String)

@RestController
class MessageResource {
	@GetMapping
	fun index(): List<Message> = listOf(
		Message("1", "Hello!"),
		Message("1", "Bonjour!"),
		Message("1", "Privet!")
	)
}

fun main(args: Array<String>) {
	runApplication<KotlinBasicWsApplication>(*args)
}

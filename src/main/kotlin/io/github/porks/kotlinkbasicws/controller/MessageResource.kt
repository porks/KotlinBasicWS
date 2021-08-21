package io.github.porks.kotlinkbasicws.controller

import io.github.porks.kotlinkbasicws.model.Message
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class MessageResource {
    @GetMapping
    fun index(): List<Message> = listOf(
        Message("1", "Hello!"),
        Message("2", "Bonjour!"),
        Message("3", "Privet!"),
        Message("4", "Bom Dia!"),
        Message("5", "Guten Morgen!"),
    )
}

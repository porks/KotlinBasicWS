package io.github.porks.kotlinkbasicws.controller

import io.github.porks.kotlinkbasicws.model.Message
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TableInfo {
    @GetMapping("/info")
    fun info(): List<Message> = listOf(
        Message("1", "Hello!"),
    )
}
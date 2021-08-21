package io.github.porks.kotlinkbasicws.controller.restapi

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class DataController {
    @GetMapping("/data/query")
    fun query(): List<Array<String>> {
        return listOf(
            arrayOf("1", "Hello!"),
            arrayOf("2", "Bonjour!"),
            arrayOf("3", "Privet!"),
            arrayOf("4", "Bom Dia!"),
            arrayOf("5", "Guten Morgen!"),
        )
    }
}
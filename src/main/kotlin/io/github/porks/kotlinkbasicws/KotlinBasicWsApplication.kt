package io.github.porks.kotlinkbasicws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class KotlinBasicWsApplication

fun main(args: Array<String>) {
	runApplication<KotlinBasicWsApplication>(*args)
}

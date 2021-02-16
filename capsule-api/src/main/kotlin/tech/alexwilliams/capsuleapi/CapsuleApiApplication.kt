package tech.alexwilliams.capsuleapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class CapsuleApiApplication

fun main(args: Array<String>) {
	runApplication<CapsuleApiApplication>(*args)
}

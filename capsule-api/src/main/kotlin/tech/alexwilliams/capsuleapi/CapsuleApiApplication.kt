package tech.alexwilliams.capsuleapi

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import tech.alexwilliams.capsuleapi.podcast.services.RssService

@SpringBootApplication
class CapsuleApiApplication

fun main(args: Array<String>) {
	runApplication<CapsuleApiApplication>(*args) {
	}
}
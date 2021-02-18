package tech.alexwilliams.capsuleapi

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.reactive.server.WebTestClient

@SpringBootTest
class CapsuleApiApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun webClientTest() {
		WebTestClient
			.bindToServer()
			.baseUrl("https://feeds.megaphone.fm/HSW7536705545")
			.build()
			.get()
			.exchange()
			.expectStatus().isOk
	}
}

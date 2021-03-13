package tech.alexwilliams.capsuleapi.config

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.CorsRegistry
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer

@Configuration
@EnableWebFlux
class CorsConfig: WebFluxConfigurer {

    @Value("\${tech.alexwilliams.cors.allowedOrigins}")
    private val allowedOrigins: String? = null

    override fun addCorsMappings(corsRegistry: CorsRegistry) {
        corsRegistry.addMapping("/**")
            .allowedOrigins(allowedOrigins)
    }
}
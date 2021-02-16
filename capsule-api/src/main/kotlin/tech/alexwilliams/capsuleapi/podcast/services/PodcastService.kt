package tech.alexwilliams.capsuleapi.podcast.services

import org.springframework.stereotype.Service

@Service
class PodcastService {
 
    fun get(): String {
        return "hello service"
    }
}
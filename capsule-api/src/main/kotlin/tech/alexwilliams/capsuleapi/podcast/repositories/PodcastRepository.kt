package tech.alexwilliams.capsuleapi.podcast.repositories

import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import tech.alexwilliams.capsuleapi.podcast.models.Podcast

public interface PodcastRepository: ReactiveMongoRepository<Podcast, String> {
}
package tech.alexwilliams.capsuleapi.podcast.services

import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tech.alexwilliams.capsuleapi.podcast.models.Podcast
import tech.alexwilliams.capsuleapi.podcast.models.PodcastEpisode
import tech.alexwilliams.capsuleapi.podcast.repositories.PodcastRepository

@Service
class PodcastService(val podcastRepository: PodcastRepository,
                     val rssService: RssService) {

    fun getAll(): Flux<Podcast> {
        return podcastRepository.findAll()
    }

    fun getById(id: String): Mono<Podcast> {
        return podcastRepository.findById(id)
    }

    fun savePodcast(podcast: Podcast): Mono<Podcast> {
        return podcastRepository.save(podcast)
    }

    fun loadPodcastRssFeed(rssUrl: String): Mono<List<PodcastEpisode>> {
        rssService.deserializePodcastRssFeed("")
        return Mono.just(emptyList())
//        return this.rssService.fetchRssFeed(rssUrl).map { rss ->
//            val podcastRssFeed = rssService.deserializePodcastRssFeed(rss)
//
//            val podcastEpisodes = listOf(
//                PodcastEpisode("12345", podcastRssFeed.channel?.title, "In this episode we discuss whats really important")
//            )
//
//            podcastEpisodes
//        }
    }
}
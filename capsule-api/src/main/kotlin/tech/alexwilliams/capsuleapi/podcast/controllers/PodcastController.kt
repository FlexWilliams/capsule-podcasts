package tech.alexwilliams.capsuleapi.podcast.controllers

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono
import tech.alexwilliams.capsuleapi.podcast.models.Podcast
import tech.alexwilliams.capsuleapi.podcast.models.PodcastEpisode
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeed

import tech.alexwilliams.capsuleapi.podcast.services.PodcastService

@RestController
@RequestMapping("/podcasts")
class PodcastController(val podcastService: PodcastService) {

  @GetMapping()
  fun getAllPodcasts(): Flux<Podcast> {
    return podcastService.getAll()
  }

  @GetMapping("{id}")
  fun getPodcastById(@PathVariable id: String): Mono<ResponseEntity<Podcast>> {
    return podcastService.getById(id)
                         .map { product -> ResponseEntity.ok(product) }
                         .defaultIfEmpty(ResponseEntity.notFound().build())
  }

  @PostMapping
  @ResponseStatus(HttpStatus.CREATED)
  fun savePodcast(@RequestBody podcast: Podcast): Mono<Podcast> {
    return podcastService.savePodcast(podcast)
  }

  @PostMapping("/add-rss")
  fun addPodcastRss(@RequestBody rssUrl: String): Mono<PodcastRssFeed> {
    return podcastService.loadPodcastRssFeed(rssUrl)
  }
}
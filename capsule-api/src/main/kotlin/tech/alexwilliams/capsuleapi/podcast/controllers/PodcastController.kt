package tech.alexwilliams.capsuleapi.podcast.controllers

import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

import tech.alexwilliams.capsuleapi.podcast.services.PodcastService

@RestController
@RequestMapping("/api/podcasts")
class PodcastController(val podcastService: PodcastService) {

  @GetMapping()
  fun get(): String {
    return podcastService.get()
  }
}
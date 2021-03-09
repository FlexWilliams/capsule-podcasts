package tech.alexwilliams.capsuleapi.podcast.services

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.XStreamException
import org.springframework.http.MediaType
import org.springframework.stereotype.Service
import reactor.core.publisher.Mono
import tech.alexwilliams.capsuleapi.podcast.config.xml.PodcastRssFeedXstreamInitializer
import tech.alexwilliams.capsuleapi.podcast.exception.PodcastRssFeedException
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeed
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToMono


@Service
class RssService {
    private var xStream: XStream? = null

    init {
        val xStreamInitializer = PodcastRssFeedXstreamInitializer()
        this.xStream = xStreamInitializer.getPodcastRssFeedXstreamInitializer()
    }

    fun fetchRssFeed(rssUrl: String): Mono<String> {
        val client = WebClient.create(rssUrl)
        return client.get()
                        .accept(MediaType.APPLICATION_XML)
                        .accept(MediaType.APPLICATION_RSS_XML)
                        .retrieve().bodyToMono()
    }

    fun deserializePodcastRssFeed(rssFeed: String): PodcastRssFeed {
        try {
            return xStream!!.fromXML(rssFeed) as PodcastRssFeed
        } catch (ex: XStreamException) { // TODO: add logging framework
            throw PodcastRssFeedException("Error deserializing podcast rss feed")
        } catch (ex: Exception) {
            throw PodcastRssFeedException("Error deserializing podcast rss feed")
        }
    }
}
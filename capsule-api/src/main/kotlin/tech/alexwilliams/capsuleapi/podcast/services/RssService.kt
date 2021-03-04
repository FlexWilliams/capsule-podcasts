package tech.alexwilliams.capsuleapi.podcast.services

import com.thoughtworks.xstream.XStream
import com.thoughtworks.xstream.XStreamException
import org.springframework.stereotype.Service
import tech.alexwilliams.capsuleapi.podcast.config.xml.XstreamInitializer
import tech.alexwilliams.capsuleapi.podcast.exception.PodcastRssFeedException
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeed

@Service
class RssService {
    private var xStream: XStream? = null

    init {
        val xStreamInitializer = XstreamInitializer()
        this.xStream = xStreamInitializer.getXstreamInstance()
    }

    fun fetchRssFeed(rssUrl: String): List<String> {
        return listOf()
    }

    fun deserializePodcastRssFeed(rssFeed: String): PodcastRssFeed {
        try {
            return xStream!!.fromXML(rssFeed) as PodcastRssFeed
        } catch (ex: XStreamException) { // TODO: add logging framework
            throw PodcastRssFeedException("Error deserializing podcast rss feed")
        }
    }
}
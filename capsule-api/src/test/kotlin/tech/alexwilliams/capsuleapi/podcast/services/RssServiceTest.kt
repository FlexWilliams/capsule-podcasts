package tech.alexwilliams.capsuleapi.podcast.services

import com.thoughtworks.xstream.XStream
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Assertions.assertThrows
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import java.io.FileReader

import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import reactor.core.publisher.Mono
import tech.alexwilliams.capsuleapi.podcast.config.xml.PodcastRssFeedXstreamInitializer
import tech.alexwilliams.capsuleapi.podcast.exception.PodcastRssFeedException
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeed
import java.io.FileNotFoundException
import java.lang.Exception
import java.util.stream.Collectors

@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class RssServiceTest @Autowired constructor(private val rssService: RssService)  {

    private var xstream: XStream? = null

    @BeforeAll
    fun setup() {
        val xStreamInitializer = PodcastRssFeedXstreamInitializer()
        xstream = xStreamInitializer.getPodcastRssFeedXstreamInitializer()
    }

    @Test
    @Throws(FileNotFoundException::class)
    fun convertXmlToObjectFromFile() {
        val podcastRssFeedFile = getPodcastRssFeedFile()
        val podcastRssFeed: PodcastRssFeed = xstream!!.fromXML(podcastRssFeedFile) as PodcastRssFeed
        assertNotNull(podcastRssFeed)
    }

    @Test
    @Throws(PodcastRssFeedException::class)
    fun convertXmlToObjectFromString() {
        val podcastRssFeedText = getPodcastRssFeedFile().readLines().stream().collect(Collectors.joining())
        Mono.just(5).map {
            val podcastRssFeed = rssService.deserializePodcastRssFeed(podcastRssFeedText)
            assertNotNull(podcastRssFeed)
        }.subscribe()

    }

    @Test
    fun throwsExceptionIfErrorDeserializingPodcast() {
        val podcastRssFeedText = ""

        val exception: Exception = assertThrows(PodcastRssFeedException::class.java) {
            rssService.deserializePodcastRssFeed(podcastRssFeedText)
        }

        assertNotNull(exception.message)
    }

    private fun getPodcastRssFeedFile(): FileReader {
        val classLoader = javaClass.classLoader

        return  FileReader(
            classLoader
                .getResource("podcast-rss-example.xml")
                .file
        )
    }
}
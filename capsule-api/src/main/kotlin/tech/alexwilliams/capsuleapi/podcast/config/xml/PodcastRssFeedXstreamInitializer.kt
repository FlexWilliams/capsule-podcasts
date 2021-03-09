package tech.alexwilliams.capsuleapi.podcast.config.xml

import com.thoughtworks.xstream.XStream
import tech.alexwilliams.capsuleapi.CapsuleApiApplication
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeed
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeedChannel
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeedChannelItem
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeedChannelItemEnclosure

class PodcastRssFeedXstreamInitializer(private var xStream: XStream? = null) {

    init {
        this.xStream = XStream()

        XStream.setupDefaultSecurity(this.xStream)

        this.xStream!!.allowTypesByWildcard(arrayOf(CapsuleApiApplication::class.java.packageName + ".**"))
        this.xStream!!.ignoreUnknownElements()
        this.xStream!!.alias("rss", PodcastRssFeed::class.java)
        this.xStream!!.alias("item", PodcastRssFeedChannelItem::class.java)

        this.xStream!!.addImplicitCollection(PodcastRssFeedChannel::class.java, "items")
        this.xStream!!.aliasAttribute(PodcastRssFeedChannelItemEnclosure::class.java, "url", "url")
        this.xStream!!.aliasAttribute(PodcastRssFeedChannelItemEnclosure::class.java, "length", "length")
        this.xStream!!.aliasAttribute(PodcastRssFeedChannelItemEnclosure::class.java, "type", "type")

    }

    fun getPodcastRssFeedXstreamInitializer(): XStream? {
        return this.xStream
    }
}
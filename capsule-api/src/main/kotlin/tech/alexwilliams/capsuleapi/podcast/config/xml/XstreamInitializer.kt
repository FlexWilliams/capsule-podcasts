package tech.alexwilliams.capsuleapi.podcast.config.xml

import com.thoughtworks.xstream.XStream
import tech.alexwilliams.capsuleapi.CapsuleApiApplication
import tech.alexwilliams.capsuleapi.podcast.models.PodcastRssFeed

class XstreamInitializer(private var xStream: XStream? = null) {

    init {
        this.xStream = XStream()

        XStream.setupDefaultSecurity(this.xStream)

        this.xStream!!.allowTypesByWildcard(arrayOf(CapsuleApiApplication::class.java.packageName + ".**"))
        this.xStream!!.ignoreUnknownElements()
        this.xStream!!.alias("rss", PodcastRssFeed::class.java)
    }

    fun getXstreamInstance(): XStream? {
        return this.xStream
    }
}
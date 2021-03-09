package tech.alexwilliams.capsuleapi.podcast.models

import com.thoughtworks.xstream.annotations.XStreamAlias
import com.thoughtworks.xstream.annotations.XStreamImplicit
import lombok.Data

@Data
class PodcastRssFeedChannelItemEnclosure(
    var url: String? = null,
    var length: Int? = null,
    var type: String? = null,
) {}

@Data
class PodcastRssFeedChannelItemImage(
    var url: String? = null,
) {}

@Data
class PodcastRssFeedChannelItem(
    var title: String? = null,
    var description: String? = null,
    var pubDate: String? = null,
    var enclosure: PodcastRssFeedChannelItemEnclosure? = null,
) {}

@Data
class PodcastRssFeedChannel( // TODO: review, should this just be the regular podcast data class?
    var title: String? = null,
    var description: String? = null,
    var image: PodcastRssFeedChannelItemImage? = null,

    @XStreamImplicit
    var items: List<PodcastRssFeedChannelItem>? = emptyList(),
) {
}

@Data
@XStreamAlias("rss")
class PodcastRssFeed(
    var channel: PodcastRssFeedChannel? = null,
) {
}
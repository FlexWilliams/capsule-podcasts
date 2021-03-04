package tech.alexwilliams.capsuleapi.podcast.models

import com.thoughtworks.xstream.annotations.XStreamAlias
import lombok.Data

@Data
class PodcastRssFeedChannel( // TODO: review, should this just be the regular podcast data class?
    var title: String? = null,
    var description: String? = null
) {
}

@Data
@XStreamAlias("rss")
class PodcastRssFeed(
    var version: String? = null,
    var channel: PodcastRssFeedChannel? = null
) {
}
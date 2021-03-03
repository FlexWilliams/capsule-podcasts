package tech.alexwilliams.capsuleapi.podcast.models

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Data
@Document("episode")
class PodcastEpisode(
    @Id
    var id: String? = null,

    var title: String? = null,

    var description: String? = null
) {
}
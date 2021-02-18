package tech.alexwilliams.capsuleapi.podcast.models

import lombok.Data
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Data
@Document("podcasts")
class Podcast(
    @Id
    var id: String? = null,

    var title: String? = null,

    var description: String? = null,

    var imageUrl: String? = null,

    var rssUrl: String? = null,
) {
}
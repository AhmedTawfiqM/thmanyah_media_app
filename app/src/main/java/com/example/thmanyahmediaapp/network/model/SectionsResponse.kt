package com.example.thmanyahmediaapp.network.model

import com.google.gson.annotations.SerializedName

enum class SectionType {
    @SerializedName("square")
    SQUARE,
    @SerializedName("2_lines_grid")
    TWO_LINES_GRID,
    @SerializedName("big_square")
    BIG_SQUARE,
    @SerializedName("queue")
    QUEUE
}

enum class ContentType {
    @SerializedName("podcast")
    PODCAST,
    @SerializedName("episode")
    EPISODE,
    @SerializedName("audio_book")
    AUDIO_BOOK,
    @SerializedName("audio_article")
    AUDIO_ARTICLE
}

data class SectionsResponse(
    @SerializedName("sections")
    val sections: List<Section>,
    @SerializedName("pagination")
    val pagination: Pagination? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("message")
    val message: String? = null
)

data class Section(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: SectionType?,
    @SerializedName("content_type")
    val contentType: ContentType,
    @SerializedName("order")
    val order: Int,
    @SerializedName("content")
    val items: List<Any>
)

data class Pagination(
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null
)

data class Podcast(
    @SerializedName("podcast_id")
    val podcastId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("episode_count")
    val episodeCount: Int? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("priority")
    val priority: Int? = null,
    @SerializedName("popularityScore")
    val popularityScore: Int? = null,
    @SerializedName("score")
    val score: Double? = null
)

data class Episode(
    @SerializedName("podcastPopularityScore")
    val podcastPopularityScore: Int? = null,
    @SerializedName("podcastPriority")
    val podcastPriority: Int? = null,
    @SerializedName("episode_id")
    val episodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("season_number")
    val seasonNumber: Int? = null,
    @SerializedName("episode_type")
    val episodeType: String? = null, // "full", "trailer"
    @SerializedName("podcast_name")
    val podcastName: String? = null,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("number")
    val number: Int? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("separated_audio_url")
    val separatedAudioUrl: String? = null,
    @SerializedName("audio_url")
    val audioUrl: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("podcast_id")
    val podcastId: String? = null,
    @SerializedName("chapters")
    val chapters: List<Any>? = null,
    @SerializedName("paid_is_early_access")
    val paidIsEarlyAccess: Boolean? = null,
    @SerializedName("paid_is_now_early_access")
    val paidIsNowEarlyAccess: Boolean? = null,
    @SerializedName("paid_is_exclusive")
    val paidIsExclusive: Boolean? = null,
    @SerializedName("paid_transcript_url")
    val paidTranscriptUrl: String? = null,
    @SerializedName("free_transcript_url")
    val freeTranscriptUrl: String? = null,
    @SerializedName("paid_is_exclusive_partially")
    val paidIsExclusivePartially: Boolean? = null,
    @SerializedName("paid_exclusive_start_time")
    val paidExclusiveStartTime: Int? = null,
    @SerializedName("paid_early_access_date")
    val paidEarlyAccessDate: String? = null,
    @SerializedName("paid_early_access_audio_url")
    val paidEarlyAccessAudioUrl: String? = null,
    @SerializedName("paid_exclusivity_type")
    val paidExclusivityType: String? = null,
    @SerializedName("score")
    val score: Double? = null
)

data class AudioBook(
    @SerializedName("audiobook_id")
    val audiobookId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: Double? = null
)

data class AudioArticle(
    @SerializedName("article_id")
    val articleId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: Double? = null
)

// Legacy data classes for search functionality
data class SearchResponse(
    @SerializedName("results")
    val results: List<SearchResult>,
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("query")
    val query: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("hasMore")
    val hasMore: Boolean,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("message")
    val message: String? = null
)

data class SearchResult(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("videoUrl")
    val videoUrl: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("type")
    val type: String,
    @SerializedName("relevanceScore")
    val relevanceScore: Double? = null
)

package com.example.thmanyahmediaapp.domain.model.search

import com.example.thmanyahmediaapp.domain.model.SectionContentType
import com.example.thmanyahmediaapp.domain.model.SectionLayout
import com.google.gson.annotations.SerializedName

data class SearchSectionsResponse(
    @SerializedName("sections")
    val sections: List<SearchSection>,
    @SerializedName("pagination")
    val pagination: SearchPagination? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("message")
    val message: String? = null
)

data class SearchSection(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: SectionLayout?,
    @SerializedName("content_type")
    val sectionContentType: SectionContentType,
    @SerializedName("order")
    val order: String,
    @SerializedName("content")
    val items: List<Any>,
)

data class SearchPagination(
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("total_pages")
    val totalPages: String? = null
)

data class SearchPodcast(
    @SerializedName("podcast_id")
    val podcastId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("episode_count")
    val episodeCount: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("priority")
    val priority: String? = null,
    @SerializedName("popularityScore")
    val popularityScore: String? = null,
    @SerializedName("score")
    val score: String? = null
)

data class SearchEpisode(
    @SerializedName("podcastPopularityScore")
    val podcastPopularityScore: String? = null,
    @SerializedName("podcastPriority")
    val podcastPriority: String? = null,
    @SerializedName("episode_id")
    val episodeId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("season_number")
    val seasonNumber: String? = null,
    @SerializedName("episode_type")
    val episodeType: String? = null,
    @SerializedName("podcast_name")
    val podcastName: String? = null,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("number")
    val number: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
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
    val paidIsExclusivePartially: Boolean? = null
)

data class SearchAudioBook(
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
    val duration: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: String? = null
)

data class SearchAudioArticle(
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
    val duration: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: String? = null
)

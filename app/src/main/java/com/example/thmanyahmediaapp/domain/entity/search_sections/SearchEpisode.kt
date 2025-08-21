package com.example.thmanyahmediaapp.domain.entity.search_sections

import com.example.thmanyahmediaapp.domain.entity.MediaEntity
import com.google.gson.annotations.SerializedName

data class SearchEpisode(
    @SerializedName("podcastPopularityScore")
    val podcastPopularityScore: String? = null,
    @SerializedName("podcastPriority")
    val podcastPriority: String? = null,
    @SerializedName("episode_id")
    override val id: String,
    @SerializedName("season_number")
    val seasonNumber: String? = null,
    @SerializedName("episode_type")
    val episodeType: String? = null,
    @SerializedName("podcast_name")
    val podcastName: String? = null,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("number")
    val number: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
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
) : MediaEntity()

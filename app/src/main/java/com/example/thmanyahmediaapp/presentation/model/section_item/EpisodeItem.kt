package com.example.thmanyahmediaapp.presentation.model.section_item

data class EpisodeItem(
    val podcastPopularityScore: String? = null,
    val podcastPriority: String? = null,
    val episodeId: String,
    val name: String,
    val seasonNumber: String? = null,
    val episodeType: String? = null,
    val podcastName: String? = null,
    val authorName: String? = null,
    val description: String? = null,
    val number: String? = null,
    val duration: String? = null,
    val avatarUrl: String? = null,
    val separatedAudioUrl: String? = null,
    val audioUrl: String? = null,
    val releaseDate: String? = null,
    val podcastId: String? = null,
    val chapters: List<Any>? = null,
    val paidIsEarlyAccess: Boolean? = null,
    val paidIsNowEarlyAccess: Boolean? = null,
    val paidIsExclusive: Boolean? = null,
    val paidTranscriptUrl: String? = null,
    val freeTranscriptUrl: String? = null,
    val paidIsExclusivePartially: Boolean? = null
)

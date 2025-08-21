package com.example.thmanyahmediaapp.presentation.model.section_item

data class EpisodeItem(
    override var id: String,
    override var name: String,
    override var description: String? = null,
    override var avatarUrl: String? = null,
    val podcastPopularityScore: String? = null,
    val podcastPriority: String? = null,
    val seasonNumber: String? = null,
    val episodeType: String? = null,
    val podcastName: String? = null,
    val authorName: String? = null,
    val number: String? = null,
    val duration: String? = null,
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
) : MediaItem()

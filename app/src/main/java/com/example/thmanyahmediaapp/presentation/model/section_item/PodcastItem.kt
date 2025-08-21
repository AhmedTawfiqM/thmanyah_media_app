package com.example.thmanyahmediaapp.presentation.model.section_item

data class PodcastItem(
    override var id: String,
    override var name: String,
    override var description: String? = null,
    override var avatarUrl: String? = null,
    val episodeCount: String? = null,
    val duration: String? = null,
    val language: String? = null,
    val priority: String? = null,
    val popularityScore: String? = null,
    val score: String? = null
) : MediaItem()

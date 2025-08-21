package com.example.thmanyahmediaapp.presentation.model.section_item

data class PodcastItem(
    val podcastId: String,
    val name: String,
    val description: String? = null,
    val avatarUrl: String? = null,
    val episodeCount: String? = null,
    val duration: String? = null,
    val language: String? = null,
    val priority: String? = null,
    val popularityScore: String? = null,
    val score: String? = null
)

package com.example.thmanyahmediaapp.domain.entity.search_sections

import com.google.gson.annotations.SerializedName

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
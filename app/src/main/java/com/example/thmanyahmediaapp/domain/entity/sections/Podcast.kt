package com.example.thmanyahmediaapp.domain.entity.sections

import com.google.gson.annotations.SerializedName

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
package com.example.thmanyahmediaapp.domain.entity.search_sections

import com.example.thmanyahmediaapp.domain.entity.MediaEntity
import com.google.gson.annotations.SerializedName

data class SearchPodcast(
    @SerializedName("podcast_id")
    override val id: String,
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
) : MediaEntity()
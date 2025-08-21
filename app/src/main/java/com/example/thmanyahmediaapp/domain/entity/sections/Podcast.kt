package com.example.thmanyahmediaapp.domain.entity.sections

import com.example.thmanyahmediaapp.domain.entity.MediaEntity
import com.google.gson.annotations.SerializedName


data class Podcast(
    @SerializedName("podcast_id")
    override val id: String,
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
    val score: Double? = null,
) : MediaEntity()
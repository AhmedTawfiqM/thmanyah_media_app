package com.example.thmanyahmediaapp.domain.entity.sections

import com.example.thmanyahmediaapp.domain.entity.MediaEntity
import com.google.gson.annotations.SerializedName

data class AudioArticle(
    @SerializedName("article_id")
    override val id: String,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: Double? = null
) : MediaEntity()
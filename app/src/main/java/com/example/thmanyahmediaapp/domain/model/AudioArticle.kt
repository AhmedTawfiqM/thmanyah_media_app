package com.example.thmanyahmediaapp.domain.model

import com.google.gson.annotations.SerializedName

data class AudioArticle(
    @SerializedName("article_id")
    val articleId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: Double? = null
)

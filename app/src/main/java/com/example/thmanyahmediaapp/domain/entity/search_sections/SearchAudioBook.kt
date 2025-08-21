package com.example.thmanyahmediaapp.domain.entity.search_sections

import com.google.gson.annotations.SerializedName

data class SearchAudioBook(
    @SerializedName("audiobook_id")
    val audiobookId: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("avatar_url")
    val avatarUrl: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: String? = null
)

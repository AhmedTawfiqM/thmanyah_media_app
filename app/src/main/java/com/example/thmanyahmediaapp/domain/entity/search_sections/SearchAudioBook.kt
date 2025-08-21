package com.example.thmanyahmediaapp.domain.entity.search_sections

import com.example.thmanyahmediaapp.domain.entity.MediaEntity
import com.google.gson.annotations.SerializedName

data class SearchAudioBook(
    @SerializedName("audiobook_id")
    override val id: String,
    @SerializedName("author_name")
    val authorName: String? = null,
    @SerializedName("duration")
    val duration: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("release_date")
    val releaseDate: String? = null,
    @SerializedName("score")
    val score: String? = null
) : MediaEntity()

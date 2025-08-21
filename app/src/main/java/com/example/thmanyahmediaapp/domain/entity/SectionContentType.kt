package com.example.thmanyahmediaapp.domain.entity

import com.google.gson.annotations.SerializedName

enum class SectionContentType {
    @SerializedName("podcast")
    PODCAST,

    @SerializedName("episode")
    EPISODE,

    @SerializedName("audio_book")
    AUDIO_BOOK,

    @SerializedName("audio_article")
    AUDIO_ARTICLE
}

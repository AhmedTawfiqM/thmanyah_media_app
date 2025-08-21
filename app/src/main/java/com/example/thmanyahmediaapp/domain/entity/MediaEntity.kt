package com.example.thmanyahmediaapp.domain.entity

import com.google.gson.annotations.SerializedName

abstract class MediaEntity {
    abstract val id: String

    @SerializedName("name")
    lateinit var name: String

    @SerializedName("description")
    val description: String? = null

    @SerializedName("avatar_url")
    val avatarUrl: String? = null
}

/** this is used for fallback media entity type */
class DefaultMediaEntity(
    @SerializedName("podcast_id")
    override val id: String,
) : MediaEntity()
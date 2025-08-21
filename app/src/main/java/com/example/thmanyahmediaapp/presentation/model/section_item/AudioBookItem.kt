package com.example.thmanyahmediaapp.presentation.model.section_item

data class AudioBookItem(
    override var id: String,
    override var name: String,
    override var description: String? = null,
    override var avatarUrl: String? = null,
    val authorName: String? = null,
    val duration: String? = null,
    val language: String? = null,
    val releaseDate: String? = null,
    val score: String? = null
) : MediaItem()

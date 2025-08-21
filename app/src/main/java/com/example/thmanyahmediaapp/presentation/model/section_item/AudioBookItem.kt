package com.example.thmanyahmediaapp.presentation.model.section_item

data class AudioBookItem(
    val audiobookId: String,
    val name: String,
    val authorName: String? = null,
    val description: String? = null,
    val avatarUrl: String? = null,
    val duration: String? = null,
    val language: String? = null,
    val releaseDate: String? = null,
    val score: String? = null
)

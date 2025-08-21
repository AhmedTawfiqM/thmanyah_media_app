package com.example.thmanyahmediaapp.domain.entity

sealed class ContentItem {
    abstract val id: String
    abstract val name: String
    abstract val description: String?
    abstract val avatar_url: String
    abstract val duration: Long?
}

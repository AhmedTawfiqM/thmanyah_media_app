package com.example.thmanyahmediaapp.presentation.model.section_item

class DefaultMediaItem(
    override var id: String,
    override var description: String?,
    override var name: String,
    override var avatarUrl: String?
) : MediaItem()
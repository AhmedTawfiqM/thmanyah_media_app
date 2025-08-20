package com.example.thmanyahmediaapp.data

import com.example.thmanyahmediaapp.domain.model.AudioArticle
import com.example.thmanyahmediaapp.domain.model.AudioBook
import com.example.thmanyahmediaapp.domain.model.ContentType
import com.example.thmanyahmediaapp.domain.model.Episode
import com.example.thmanyahmediaapp.domain.model.Podcast
import com.google.gson.Gson

object SectionParser {
    private val gson = Gson()

    fun parse(content: List<Any>, contentType: ContentType): List<Any> {
        return content.mapNotNull { item ->
            when (item) {
                is Map<*, *> -> {
                    val json = gson.toJson(item)
                    when (contentType) {
                        ContentType.PODCAST -> gson.fromJson(json, Podcast::class.java)
                        ContentType.EPISODE -> gson.fromJson(json, Episode::class.java)
                        ContentType.AUDIO_BOOK -> gson.fromJson(json, AudioBook::class.java)
                        ContentType.AUDIO_ARTICLE -> gson.fromJson(json, AudioArticle::class.java)
                    }
                }
                else -> item
            }
        }
    }
}
package com.example.thmanyahmediaapp.utils

import com.example.thmanyahmediaapp.network.model.*
import com.google.gson.Gson

object ContentParser {
    private val gson = Gson()

    fun parseContentForSection(content: List<Any>, contentType: ContentType): List<Any> {
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

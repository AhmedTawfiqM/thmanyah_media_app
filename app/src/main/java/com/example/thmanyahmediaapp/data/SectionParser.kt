package com.example.thmanyahmediaapp.data

import com.example.thmanyahmediaapp.domain.model.AudioArticle
import com.example.thmanyahmediaapp.domain.model.AudioBook
import com.example.thmanyahmediaapp.domain.model.SectionContentType
import com.example.thmanyahmediaapp.domain.model.Episode
import com.example.thmanyahmediaapp.domain.model.Podcast
import com.google.gson.Gson

object SectionParser {
    private val gson = Gson()

    fun parse(content: List<Any>, sectionContentType: SectionContentType): List<Any> {
        return content.mapNotNull { item ->
            when (item) {
                is Map<*, *> -> {
                    val json = gson.toJson(item)
                    when (sectionContentType) {
                        SectionContentType.PODCAST -> gson.fromJson(json, Podcast::class.java)
                        SectionContentType.EPISODE -> gson.fromJson(json, Episode::class.java)
                        SectionContentType.AUDIO_BOOK -> gson.fromJson(json, AudioBook::class.java)
                        SectionContentType.AUDIO_ARTICLE -> gson.fromJson(json, AudioArticle::class.java)
                    }
                }
                else -> item
            }
        }
    }
}
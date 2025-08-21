package com.example.thmanyahmediaapp.data

import com.example.thmanyahmediaapp.domain.model.sections.AudioArticle
import com.example.thmanyahmediaapp.domain.model.sections.AudioBook
import com.example.thmanyahmediaapp.domain.model.sections.SectionContentType
import com.example.thmanyahmediaapp.domain.model.sections.Episode
import com.example.thmanyahmediaapp.domain.model.sections.Podcast
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
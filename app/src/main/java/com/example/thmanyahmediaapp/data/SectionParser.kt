package com.example.thmanyahmediaapp.data

import com.example.thmanyahmediaapp.domain.entity.DefaultMediaEntity
import com.example.thmanyahmediaapp.domain.entity.MediaEntity
import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.search_sections.SearchAudioArticle
import com.example.thmanyahmediaapp.domain.entity.search_sections.SearchAudioBook
import com.example.thmanyahmediaapp.domain.entity.search_sections.SearchEpisode
import com.example.thmanyahmediaapp.domain.entity.search_sections.SearchPodcast
import com.example.thmanyahmediaapp.domain.entity.sections.AudioArticle
import com.example.thmanyahmediaapp.domain.entity.sections.AudioBook
import com.example.thmanyahmediaapp.domain.entity.sections.Episode
import com.example.thmanyahmediaapp.domain.entity.sections.Podcast
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

object SearchSectionParser {
    private val gson = Gson()

    fun parse(content: List<Any>, sectionContentType: SectionContentType?): List<Any> {
        return content.mapNotNull { item ->
            when (item) {
                is Map<*, *> -> {
                    val json = gson.toJson(item)
                    when (sectionContentType) {
                        SectionContentType.PODCAST -> gson.fromJson(json, SearchPodcast::class.java)
                        SectionContentType.EPISODE -> gson.fromJson(json, SearchEpisode::class.java)
                        SectionContentType.AUDIO_BOOK -> gson.fromJson(json, SearchAudioBook::class.java)
                        SectionContentType.AUDIO_ARTICLE -> gson.fromJson(json, SearchAudioArticle::class.java)
                        null -> gson.fromJson(json, DefaultMediaEntity::class.java)
                    }
                }
                else -> item
            }
        }
    }
}


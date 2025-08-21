package com.example.thmanyahmediaapp.domain.entity.search_sections

import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.SectionLayout
import com.google.gson.annotations.SerializedName

data class SearchSection(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: SectionLayout?,
    @SerializedName("content_type")
    val sectionContentType: SectionContentType?,
    @SerializedName("order")
    val order: String,
    @SerializedName("content")
    val items: List<Any>,
)
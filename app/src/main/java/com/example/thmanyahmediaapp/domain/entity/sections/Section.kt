package com.example.thmanyahmediaapp.domain.entity.sections

import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.SectionLayout
import com.google.gson.annotations.SerializedName

data class Section(
    @SerializedName("name")
    val name: String,
    @SerializedName("type")
    val type: SectionLayout?,
    @SerializedName("content_type")
    val sectionContentType: SectionContentType,
    @SerializedName("order")
    val order: Int,
    @SerializedName("content")
    val items: List<Any>
)
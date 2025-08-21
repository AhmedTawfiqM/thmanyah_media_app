package com.example.thmanyahmediaapp.domain.model.sections

import com.google.gson.annotations.SerializedName

data class SectionsResponse(
    @SerializedName("sections")
    val sections: List<Section>,
    @SerializedName("pagination")
    val pagination: Pagination? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("message")
    val message: String? = null
)

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

data class Pagination(
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null
)
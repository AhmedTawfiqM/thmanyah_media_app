package com.example.thmanyahmediaapp.domain.model

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


// Legacy data classes for search functionality
data class SearchResponse(
    @SerializedName("results")
    val results: List<SearchResult>,
    @SerializedName("totalCount")
    val totalCount: Int,
    @SerializedName("query")
    val query: String,
    @SerializedName("page")
    val page: Int,
    @SerializedName("hasMore")
    val hasMore: Boolean,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("message")
    val message: String? = null
)

data class SearchResult(
    @SerializedName("id")
    val id: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("imageUrl")
    val imageUrl: String? = null,
    @SerializedName("videoUrl")
    val videoUrl: String? = null,
    @SerializedName("duration")
    val duration: Int? = null,
    @SerializedName("type")
    val type: String,
    @SerializedName("relevanceScore")
    val relevanceScore: Double? = null
)

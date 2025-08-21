package com.example.thmanyahmediaapp.data.model

import com.example.thmanyahmediaapp.domain.entity.sections.Section
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

data class Pagination(
    @SerializedName("next_page")
    val nextPage: String? = null,
    @SerializedName("total_pages")
    val totalPages: Int? = null
)
package com.example.thmanyahmediaapp.data.model

import com.example.thmanyahmediaapp.domain.entity.search_sections.SearchSection
import com.google.gson.annotations.SerializedName

data class SearchSectionsResponse(
    @SerializedName("sections")
    val sections: List<SearchSection>,
)
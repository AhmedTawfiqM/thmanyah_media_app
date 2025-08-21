package com.example.thmanyahmediaapp.presentation.model.section_item

import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.SectionLayout

data class SectionItem(
    val name: String,
    val type: SectionLayout?,
    val sectionContentType: SectionContentType?,
    val order: Int,
    val items: List<Any>
)

package com.example.thmanyahmediaapp.presentation.screen.shared.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.presentation.screen.shared.ContentCard

@Composable
fun QueueLayout(
    content: List<Any>,
    sectionContentType: SectionContentType
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(content) { item ->
            ContentCard(
                content = item,
                sectionContentType = sectionContentType,
                modifier = Modifier
                    .width(160.dp)
                    .height(200.dp)
            )
        }
    }
}
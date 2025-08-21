package com.example.thmanyahmediaapp.presentation.screen.home.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.model.SectionContentType
import com.example.thmanyahmediaapp.presentation.screen.home.ContentCard

@Composable
fun SquareGrid(
    content: List<Any>,
    sectionContentType: SectionContentType
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(content) { item ->
            ContentCard(
                content = item,
                sectionContentType = sectionContentType,
                modifier = Modifier
                    .width(180.dp)
                    .aspectRatio(1f)
            )
        }
    }
}
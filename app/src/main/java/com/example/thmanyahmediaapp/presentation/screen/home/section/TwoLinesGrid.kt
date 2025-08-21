package com.example.thmanyahmediaapp.presentation.screen.home.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.model.SectionContentType
import com.example.thmanyahmediaapp.presentation.screen.home.ContentCard

@Composable
fun TwoLinesGrid(
    content: List<Any>,
    sectionContentType: SectionContentType
) {
    LazyHorizontalGrid(
        rows = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(250.dp)
    ) {
        items(content) { item ->
            ContentCard(
                content = item,
                sectionContentType = sectionContentType,
                modifier = Modifier.width(160.dp)
            )
        }
    }
}
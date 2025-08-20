package com.example.thmanyahmediaapp.screen.section

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.network.model.ContentType
import com.example.thmanyahmediaapp.ui.components.ContentCard

@Composable
fun TwoLinesGrid(
    content: List<Any>,
    contentType: ContentType
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(300.dp)
    ) {
        items(content) { item ->
            ContentCard(
                content = item,
                contentType = contentType,
                modifier = Modifier.aspectRatio(1.5f)
            )
        }
    }
}
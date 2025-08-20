package com.example.thmanyahmediaapp.presentation.screen.home.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.model.Section
import com.example.thmanyahmediaapp.domain.model.SectionType

@Composable
fun SectionContent(
    section: Section,
    modifier: Modifier = Modifier
) {
    val items = section.items
    val sectionType = section.contentType

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = section.name+" (${section.type})",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        when (section.type) {
            SectionType.SQUARE -> SquareGrid(items, sectionType)
            SectionType.TWO_LINES_GRID -> TwoLinesGrid(items, sectionType)
            SectionType.BIG_SQUARE,
            SectionType.BIGSQUARE -> BigSquareLayout(items, sectionType)
            SectionType.QUEUE -> QueueLayout(items, sectionType)
            null -> {}
        }
    }
}
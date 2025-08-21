package com.example.thmanyahmediaapp.presentation.screen.shared.section

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.model.sections.Section
import com.example.thmanyahmediaapp.domain.model.sections.SectionLayout

@Composable
fun SectionContent(
    section: Section,
    modifier: Modifier = Modifier
) {
    val items = section.items
    val sectionType = section.sectionContentType

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Text(
            text = section.name,
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp)
        )

        when (section.type) {
            SectionLayout.SQUARE -> SquareGrid(items, sectionType)
            SectionLayout.TWO_LINES_GRID -> TwoLinesGrid(items, sectionType)
            SectionLayout.BIG_SQUARE,
            SectionLayout.BIGSQUARE -> BigSquareLayout(items, sectionType)
            SectionLayout.QUEUE -> QueueLayout(items, sectionType)
            null -> {}
        }
    }
}
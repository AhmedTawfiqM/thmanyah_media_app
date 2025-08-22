package com.example.thmanyahmediaapp.presentation.screen.shared

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.SectionLayout
import com.example.thmanyahmediaapp.presentation.model.section_item.SectionItem
import com.example.thmanyahmediaapp.presentation.model.section_item.PodcastItem
import com.example.thmanyahmediaapp.presentation.model.section_item.EpisodeItem
import com.example.thmanyahmediaapp.presentation.model.section_item.AudioBookItem
import com.example.thmanyahmediaapp.presentation.model.section_item.AudioArticleItem
import com.example.thmanyahmediaapp.presentation.model.section_item.MediaItem
import com.example.thmanyahmediaapp.presentation.screen.shared.content.AudioArticleCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.AudioBookCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.EpisodeCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.MediaCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.PodcastCard
import com.example.thmanyahmediaapp.presentation.screen.shared.section.BigSquareLayout
import com.example.thmanyahmediaapp.presentation.screen.shared.section.QueueLayout
import com.example.thmanyahmediaapp.presentation.screen.shared.section.SquareGrid
import com.example.thmanyahmediaapp.presentation.screen.shared.section.TwoLinesGrid


@Composable
fun SectionContent(
    section: SectionItem,
    modifier: Modifier = Modifier
) {
    val items = section.items
    val sectionType = section.sectionContentType

    Column(
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 8.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            Text(
                text = section.name,
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.Bold,
            )
            Icon(
                imageVector = Icons.AutoMirrored.Filled.KeyboardArrowRight,
                contentDescription = "expand Icon"
            )
        }

        when (section.type) {
            SectionLayout.SQUARE -> SquareGrid(items, sectionType)
            SectionLayout.TWO_LINES_GRID -> TwoLinesGrid(items, sectionType)
            SectionLayout.BIG_SQUARE,
            SectionLayout.BIGSQUARE -> BigSquareLayout(items, sectionType)

            SectionLayout.QUEUE -> QueueLayout(items, sectionType)
            null -> QueueLayout(items, sectionType)
        }
    }
}

@Composable
fun SectionContentCard(
    content: Any,
    sectionContentType: SectionContentType?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        when (sectionContentType) {
            SectionContentType.PODCAST -> PodcastCard(content as? PodcastItem ?: return@Card)
            SectionContentType.EPISODE -> EpisodeCard(content as? EpisodeItem ?: return@Card)
            SectionContentType.AUDIO_BOOK -> AudioBookCard(content as? AudioBookItem ?: return@Card)
            SectionContentType.AUDIO_ARTICLE -> AudioArticleCard(
                content as? AudioArticleItem ?: return@Card
            )

            null -> MediaCard(content as? MediaItem ?: return@Card)
        }
    }
}
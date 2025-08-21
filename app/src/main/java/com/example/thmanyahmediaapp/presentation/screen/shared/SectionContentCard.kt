package com.example.thmanyahmediaapp.presentation.screen.shared

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.entity.sections.AudioArticle
import com.example.thmanyahmediaapp.domain.entity.sections.AudioBook
import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.SectionLayout
import com.example.thmanyahmediaapp.domain.entity.sections.Episode
import com.example.thmanyahmediaapp.domain.entity.sections.Podcast
import com.example.thmanyahmediaapp.domain.entity.sections.Section
import com.example.thmanyahmediaapp.presentation.model.section_item.SectionItem
import com.example.thmanyahmediaapp.presentation.screen.shared.content.AudioArticleCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.AudioBookCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.EpisodeCard
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

@Composable
fun SectionContentCard(
    content: Any,
    sectionContentType: SectionContentType?,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        when (sectionContentType) {
            SectionContentType.PODCAST -> PodcastCard(content as? Podcast ?: return@Card)
            SectionContentType.EPISODE -> EpisodeCard(content as? Episode ?: return@Card)
            SectionContentType.AUDIO_BOOK -> AudioBookCard(content as? AudioBook ?: return@Card)
            SectionContentType.AUDIO_ARTICLE -> AudioArticleCard(content as? AudioArticle ?: return@Card)
            null -> PodcastCard(content as? Podcast ?: return@Card)
        }
    }
}
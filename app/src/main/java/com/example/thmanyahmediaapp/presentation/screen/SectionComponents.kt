package com.example.thmanyahmediaapp.presentation.screen

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.model.AudioArticle
import com.example.thmanyahmediaapp.domain.model.AudioBook
import com.example.thmanyahmediaapp.domain.model.ContentType
import com.example.thmanyahmediaapp.domain.model.Episode
import com.example.thmanyahmediaapp.domain.model.Podcast
import com.example.thmanyahmediaapp.domain.model.Section
import com.example.thmanyahmediaapp.domain.model.SectionType
import com.example.thmanyahmediaapp.presentation.screen.content.AudioArticleCard
import com.example.thmanyahmediaapp.presentation.screen.content.AudioBookCard
import com.example.thmanyahmediaapp.presentation.screen.content.EpisodeCard
import com.example.thmanyahmediaapp.presentation.screen.content.PodcastCard
import com.example.thmanyahmediaapp.presentation.screen.section.BigSquareLayout
import com.example.thmanyahmediaapp.presentation.screen.section.QueueLayout
import com.example.thmanyahmediaapp.presentation.screen.section.SquareGrid
import com.example.thmanyahmediaapp.presentation.screen.section.TwoLinesGrid

@Composable
fun SectionContent(
    section: Section,
    modifier: Modifier = Modifier
) {
    val parsedContent = section.items

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
            SectionType.SQUARE -> SquareGrid(parsedContent, section.contentType)
            SectionType.TWO_LINES_GRID -> TwoLinesGrid(parsedContent, section.contentType)
            SectionType.BIG_SQUARE -> BigSquareLayout(parsedContent, section.contentType)
            SectionType.QUEUE -> QueueLayout(parsedContent, section.contentType)
            null -> {}
        }
    }
}

@Composable
fun ContentCard(
    content: Any,
    contentType: ContentType,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier,
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(12.dp)
    ) {
        when (contentType) {
            ContentType.PODCAST -> PodcastCard(content as? Podcast ?: return@Card)
            ContentType.EPISODE -> EpisodeCard(content as? Episode ?: return@Card)
            ContentType.AUDIO_BOOK -> AudioBookCard(content as? AudioBook ?: return@Card)
            ContentType.AUDIO_ARTICLE -> AudioArticleCard(content as? AudioArticle ?: return@Card)
        }
    }
}
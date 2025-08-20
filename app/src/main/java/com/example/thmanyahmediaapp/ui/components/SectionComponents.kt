package com.example.thmanyahmediaapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.network.model.*
import com.example.thmanyahmediaapp.screen.content.AudioArticleCard
import com.example.thmanyahmediaapp.screen.content.AudioBookCard
import com.example.thmanyahmediaapp.screen.content.EpisodeCard
import com.example.thmanyahmediaapp.screen.content.PodcastCard
import com.example.thmanyahmediaapp.screen.section.BigSquareLayout
import com.example.thmanyahmediaapp.screen.section.QueueLayout
import com.example.thmanyahmediaapp.screen.section.SquareGrid
import com.example.thmanyahmediaapp.screen.section.TwoLinesGrid

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
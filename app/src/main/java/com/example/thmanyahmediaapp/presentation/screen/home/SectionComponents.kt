package com.example.thmanyahmediaapp.presentation.screen.home

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
import com.example.thmanyahmediaapp.presentation.screen.home.content.AudioArticleCard
import com.example.thmanyahmediaapp.presentation.screen.home.content.AudioBookCard
import com.example.thmanyahmediaapp.presentation.screen.home.content.EpisodeCard
import com.example.thmanyahmediaapp.presentation.screen.home.content.PodcastCard
import com.example.thmanyahmediaapp.presentation.screen.home.section.BigSquareLayout
import com.example.thmanyahmediaapp.presentation.screen.home.section.QueueLayout
import com.example.thmanyahmediaapp.presentation.screen.home.section.SquareGrid
import com.example.thmanyahmediaapp.presentation.screen.home.section.TwoLinesGrid



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
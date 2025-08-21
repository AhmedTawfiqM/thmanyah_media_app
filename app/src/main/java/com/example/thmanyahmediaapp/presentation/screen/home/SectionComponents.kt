package com.example.thmanyahmediaapp.presentation.screen.home

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.model.AudioArticle
import com.example.thmanyahmediaapp.domain.model.AudioBook
import com.example.thmanyahmediaapp.domain.model.SectionContentType
import com.example.thmanyahmediaapp.domain.model.Episode
import com.example.thmanyahmediaapp.domain.model.Podcast
import com.example.thmanyahmediaapp.presentation.screen.home.content.AudioArticleCard
import com.example.thmanyahmediaapp.presentation.screen.home.content.AudioBookCard
import com.example.thmanyahmediaapp.presentation.screen.home.content.EpisodeCard
import com.example.thmanyahmediaapp.presentation.screen.home.content.PodcastCard


@Composable
fun ContentCard(
    content: Any,
    sectionContentType: SectionContentType,
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
        }
    }
}
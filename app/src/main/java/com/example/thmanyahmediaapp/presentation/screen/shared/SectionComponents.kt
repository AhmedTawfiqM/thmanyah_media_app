package com.example.thmanyahmediaapp.presentation.screen.shared

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.thmanyahmediaapp.domain.model.sections.AudioArticle
import com.example.thmanyahmediaapp.domain.model.sections.AudioBook
import com.example.thmanyahmediaapp.domain.model.sections.SectionContentType
import com.example.thmanyahmediaapp.domain.model.sections.Episode
import com.example.thmanyahmediaapp.domain.model.sections.Podcast
import com.example.thmanyahmediaapp.presentation.screen.shared.content.AudioArticleCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.AudioBookCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.EpisodeCard
import com.example.thmanyahmediaapp.presentation.screen.shared.content.PodcastCard

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
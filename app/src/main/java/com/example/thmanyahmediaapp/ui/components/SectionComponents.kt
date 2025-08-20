package com.example.thmanyahmediaapp.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.example.thmanyahmediaapp.network.model.*

@Composable
fun SectionContent(
    section: Section,
    modifier: Modifier = Modifier
) {
    // Content is already parsed in the repository layer
    val parsedContent = section.content

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
        }
    }
}

@Composable
private fun SquareGrid(
    content: List<Any>,
    contentType: ContentType
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier.height(400.dp)
    ) {
        items(content) { item ->
            ContentCard(
                content = item,
                contentType = contentType,
                modifier = Modifier.aspectRatio(1f)
            )
        }
    }
}

@Composable
private fun TwoLinesGrid(
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

@Composable
private fun BigSquareLayout(
    content: List<Any>,
    contentType: ContentType
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items(content) { item ->
            ContentCard(
                content = item,
                contentType = contentType,
                modifier = Modifier
                    .fillParentMaxWidth(0.8f)
                    .aspectRatio(1f)
            )
        }
    }
}

@Composable
private fun QueueLayout(
    content: List<Any>,
    contentType: ContentType
) {
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        items(content) { item ->
            ContentCard(
                content = item,
                contentType = contentType,
                modifier = Modifier
                    .width(160.dp)
                    .height(200.dp)
            )
        }
    }
}

@Composable
private fun ContentCard(
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
            ContentType.PODCAST -> PodcastCard(content as? Podcast ?: return)
            ContentType.EPISODE -> EpisodeCard(content as? Episode ?: return)
            ContentType.AUDIO_BOOK -> AudioBookCard(content as? AudioBook ?: return)
            ContentType.AUDIO_ARTICLE -> AudioArticleCard(content as? AudioArticle ?: return)
        }
    }
}

@Composable
private fun PodcastCard(podcast: Podcast) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        AsyncImage(
            model = podcast.avatarUrl,
            contentDescription = podcast.name,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = podcast.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        podcast.episodeCount?.let { count ->
            Text(
                text = "$count episodes",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}

@Composable
private fun EpisodeCard(episode: Episode) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        AsyncImage(
            model = episode.avatarUrl,
            contentDescription = episode.name,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = episode.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        episode.podcastName?.let { podcastName ->
            Text(
                text = podcastName,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        episode.duration?.let { duration ->
            Text(
                text = "${duration / 60} min",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun AudioBookCard(audioBook: AudioBook) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        AsyncImage(
            model = audioBook.avatarUrl,
            contentDescription = audioBook.name,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = audioBook.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        audioBook.authorName?.let { author ->
            Text(
                text = "By $author",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        audioBook.duration?.let { duration ->
            Text(
                text = "${duration / 3600}h ${(duration % 3600) / 60}m",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

@Composable
private fun AudioArticleCard(audioArticle: AudioArticle) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(12.dp)
    ) {
        AsyncImage(
            model = audioArticle.avatarUrl,
            contentDescription = audioArticle.name,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .clip(RoundedCornerShape(8.dp)),
            contentScale = ContentScale.Crop
        )

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = audioArticle.name,
            style = MaterialTheme.typography.titleMedium,
            fontWeight = FontWeight.Bold,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )

        audioArticle.authorName?.let { author ->
            Text(
                text = "By $author",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }

        audioArticle.duration?.let { duration ->
            Text(
                text = "${duration / 60} min",
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.primary
            )
        }
    }
}

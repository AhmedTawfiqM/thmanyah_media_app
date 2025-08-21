package com.example.thmanyahmediaapp.presentation.model.mappers

import com.example.thmanyahmediaapp.data.model.Section
import com.example.thmanyahmediaapp.domain.entity.sections.*
import com.example.thmanyahmediaapp.domain.entity.search_sections.*
import com.example.thmanyahmediaapp.presentation.model.section_item.AudioArticleItem
import com.example.thmanyahmediaapp.presentation.model.section_item.AudioBookItem
import com.example.thmanyahmediaapp.presentation.model.section_item.EpisodeItem
import com.example.thmanyahmediaapp.presentation.model.section_item.PodcastItem
import com.example.thmanyahmediaapp.presentation.model.section_item.SectionItem


fun Section.toSectionItem(): SectionItem {
    return SectionItem(
        name = name,
        type = type,
        sectionContentType = sectionContentType,
        order = order,
        items = items
    )
}

fun SearchSection.toSectionItem(): SectionItem {
    return SectionItem(
        name = name,
        type = type,
        sectionContentType = sectionContentType,
        order = order.toIntOrNull() ?: 0,
        items = items
    )
}

// Map Podcast models to UI items
fun Podcast.toPodcastItem(): PodcastItem {
    return PodcastItem(
        podcastId = podcastId,
        name = name,
        description = description,
        avatarUrl = avatarUrl,
        episodeCount = episodeCount?.toString(),
        duration = duration?.toString(),
        language = language,
        priority = priority?.toString(),
        popularityScore = popularityScore?.toString(),
        score = score?.toString()
    )
}

fun SearchPodcast.toPodcastItem(): PodcastItem {
    return PodcastItem(
        podcastId = podcastId,
        name = name,
        description = description,
        avatarUrl = avatarUrl,
        episodeCount = episodeCount,
        duration = duration,
        language = language,
        priority = priority,
        popularityScore = popularityScore,
        score = score
    )
}

// Map Episode models to UI items
fun Episode.toEpisodeItem(): EpisodeItem {
    return EpisodeItem(
        podcastPopularityScore = podcastPopularityScore?.toString(),
        podcastPriority = podcastPriority?.toString(),
        episodeId = episodeId,
        name = name,
        seasonNumber = seasonNumber?.toString(),
        episodeType = episodeType,
        podcastName = podcastName,
        authorName = authorName,
        description = description,
        number = number?.toString(),
        duration = duration?.toString(),
        avatarUrl = avatarUrl,
        separatedAudioUrl = separatedAudioUrl,
        audioUrl = audioUrl,
        releaseDate = releaseDate,
        podcastId = podcastId,
        chapters = chapters,
        paidIsEarlyAccess = paidIsEarlyAccess,
        paidIsNowEarlyAccess = paidIsNowEarlyAccess,
        paidIsExclusive = paidIsExclusive,
        paidTranscriptUrl = paidTranscriptUrl,
        freeTranscriptUrl = freeTranscriptUrl,
        paidIsExclusivePartially = paidIsExclusivePartially
    )
}

fun SearchEpisode.toEpisodeItem(): EpisodeItem {
    return EpisodeItem(
        podcastPopularityScore = podcastPopularityScore,
        podcastPriority = podcastPriority,
        episodeId = episodeId,
        name = name,
        seasonNumber = seasonNumber,
        episodeType = episodeType,
        podcastName = podcastName,
        authorName = authorName,
        description = description,
        number = number,
        duration = duration,
        avatarUrl = avatarUrl,
        separatedAudioUrl = separatedAudioUrl,
        audioUrl = audioUrl,
        releaseDate = releaseDate,
        podcastId = podcastId,
        chapters = chapters,
        paidIsEarlyAccess = paidIsEarlyAccess,
        paidIsNowEarlyAccess = paidIsNowEarlyAccess,
        paidIsExclusive = paidIsExclusive,
        paidTranscriptUrl = paidTranscriptUrl,
        freeTranscriptUrl = freeTranscriptUrl,
        paidIsExclusivePartially = paidIsExclusivePartially
    )
}

fun AudioBook.toAudioBookItem(): AudioBookItem {
    return AudioBookItem(
        audiobookId = audiobookId,
        name = name,
        authorName = authorName,
        description = description,
        avatarUrl = avatarUrl,
        duration = duration?.toString(),
        language = language,
        releaseDate = releaseDate,
        score = score?.toString()
    )
}

fun SearchAudioBook.toAudioBookItem(): AudioBookItem {
    return AudioBookItem(
        audiobookId = audiobookId,
        name = name,
        authorName = authorName,
        description = description,
        avatarUrl = avatarUrl,
        duration = duration,
        language = language,
        releaseDate = releaseDate,
        score = score
    )
}

fun AudioArticle.toAudioArticleItem(): AudioArticleItem {
    return AudioArticleItem(
        articleId = articleId,
        name = name,
        authorName = authorName,
        description = description,
        avatarUrl = avatarUrl,
        duration = duration?.toString(),
        releaseDate = releaseDate,
        score = score?.toString()
    )
}

fun SearchAudioArticle.toAudioArticleItem(): AudioArticleItem {
    return AudioArticleItem(
        articleId = articleId,
        name = name,
        authorName = authorName,
        description = description,
        avatarUrl = avatarUrl,
        duration = duration,
        releaseDate = releaseDate,
        score = score
    )
}

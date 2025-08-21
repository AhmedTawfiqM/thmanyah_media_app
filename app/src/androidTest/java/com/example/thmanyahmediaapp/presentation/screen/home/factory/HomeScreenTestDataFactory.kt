package com.example.thmanyahmediaapp.presentation.screen.home.factory

import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.sections.Section
import io.mockk.mockk

/**
 * Factory class for creating test data objects.
 * This centralizes test data creation and makes it easy to modify test scenarios.
 *
 * Benefits:
 * - Consistent test data across different test classes
 * - Easy to modify when domain models change
 * - Reduces code duplication
 * - Makes tests more readable
 */
object HomeScreenTestDataFactory {

    /**
     * Creates a basic section with minimal data
     */
    fun createBasicSection(
        name: String = "Test Section",
        order: Int = 1,
        contentType: SectionContentType = SectionContentType.PODCAST,
        itemCount: Int = 3
    ): Section {
        return Section(
            name = name,
            type = null,
            sectionContentType = contentType,
            order = order,
            items = createMockItems(itemCount)
        )
    }

    /**
     * Creates a podcast section specifically
     */
    fun createPodcastSection(
        name: String = "Podcasts",
        order: Int = 1,
        itemCount: Int = 5
    ): Section {
        return createBasicSection(
            name = name,
            order = order,
            contentType = SectionContentType.PODCAST,
            itemCount = itemCount
        )
    }

    /**
     * Creates an audio book section specifically
     */
    fun createAudioBookSection(
        name: String = "Audio Books",
        order: Int = 2,
        itemCount: Int = 3
    ): Section {
        return createBasicSection(
            name = name,
            order = order,
            contentType = SectionContentType.AUDIO_BOOK,
            itemCount = itemCount
        )
    }

    /**
     * Creates an episode section specifically
     */
    fun createEpisodeSection(
        name: String = "Episodes",
        order: Int = 3,
        itemCount: Int = 8
    ): Section {
        return createBasicSection(
            name = name,
            order = order,
            contentType = SectionContentType.EPISODE,
            itemCount = itemCount
        )
    }

    /**
     * Creates a list of mixed content type sections
     */
    fun createMixedSections(): List<Section> {
        return listOf(
            createPodcastSection("Popular Podcasts", 1, 4),
            createAudioBookSection("Trending Audio Books", 2, 6),
            createEpisodeSection("Latest Episodes", 3, 7),
            createPodcastSection("Tech Podcasts", 4, 3)
        )
    }

    /**
     * Creates a large list of sections for testing scrolling and pagination
     */
    fun createLargeSectionsList(count: Int = 20): List<Section> {
        return (1..count).map { index ->
            val contentTypes = SectionContentType.values()
            val contentType = contentTypes[index % contentTypes.size]

            createBasicSection(
                name = "Section $index",
                order = index,
                contentType = contentType,
                itemCount = (2..8).random()
            )
        }
    }

    /**
     * Creates an empty sections list for testing empty states
     */
    fun createEmptySectionsList(): List<Section> {
        return emptyList()
    }

    /**
     * Creates sections with empty items for testing edge cases
     */
    fun createSectionsWithEmptyItems(): List<Section> {
        return listOf(
            Section(
                name = "Empty Podcasts",
                type = null,
                sectionContentType = SectionContentType.PODCAST,
                order = 1,
                items = emptyList()
            ),
            Section(
                name = "Empty Audio Books",
                type = null,
                sectionContentType = SectionContentType.AUDIO_BOOK,
                order = 2,
                items = emptyList()
            )
        )
    }

    /**
     * Creates mock items for sections
     */
    private fun createMockItems(count: Int): List<Any> {
        return (1..count).map { mockk<Any>() }
    }

    /**
     * Scenario-based factories for common test cases
     */
    object Scenarios {

        fun successfulHomeLoad(): List<Section> = createMixedSections()

        fun emptyHomeLoad(): List<Section> = createEmptySectionsList()

        fun largeDataSet(): List<Section> = createLargeSectionsList(50)

        fun singleSection(): List<Section> = listOf(createPodcastSection())

        fun sectionsWithEmptyContent(): List<Section> = createSectionsWithEmptyItems()

        fun paginationTestData(): List<Section> = createLargeSectionsList(15)
    }
}

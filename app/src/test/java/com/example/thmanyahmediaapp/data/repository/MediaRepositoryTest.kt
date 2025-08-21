package com.example.thmanyahmediaapp.data.repository

import com.example.thmanyahmediaapp.data.SearchSectionParser
import com.example.thmanyahmediaapp.data.SectionParser
import com.example.thmanyahmediaapp.data.model.Pagination
import com.example.thmanyahmediaapp.data.model.SearchSectionsResponse
import com.example.thmanyahmediaapp.data.model.SectionsResponse
import com.example.thmanyahmediaapp.data.network.ApiResult
import com.example.thmanyahmediaapp.data.network.HomeApiService
import com.example.thmanyahmediaapp.data.network.SearchApiService
import com.example.thmanyahmediaapp.domain.entity.SectionContentType
import com.example.thmanyahmediaapp.domain.entity.search_sections.SearchSection
import com.example.thmanyahmediaapp.domain.entity.sections.Section
import io.mockk.*
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import retrofit2.Response

class MediaRepositoryTest {

    private lateinit var homeApiService: HomeApiService
    private lateinit var searchApiService: SearchApiService
    private lateinit var mediaRepository: MediaRepository

    @Before
    fun setup() {
        homeApiService = mockk()
        searchApiService = mockk()
        mediaRepository = MediaRepository(homeApiService, searchApiService)

        // Mock the static parser methods
        mockkObject(SectionParser)
        mockkObject(SearchSectionParser)
    }

    @After
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun `getHomeSections returns success when API call succeeds with valid data`() = runTest {
        // Given
        val page = 1
        val limit = 10
        val mockItems = listOf<Any>() // Mock items
        val mockSection = Section(
            name = "Test Section",
            type = null,
            sectionContentType = SectionContentType.PODCAST,
            order = 1,
            items = mockItems
        )
        val mockSectionsResponse = SectionsResponse(
            sections = listOf(mockSection),
            pagination = Pagination(nextPage = "2", totalPages = 5),
            status = "success",
            message = "Data retrieved successfully"
        )
        val mockResponse = mockk<Response<SectionsResponse>>()
        val parsedItems = listOf<Any>() // Mock parsed items

        every { mockResponse.body() } returns mockSectionsResponse
        coEvery { homeApiService.getHomeSections(page, limit) } returns mockResponse
        every {
            SectionParser.parse(mockItems, SectionContentType.PODCAST)
        } returns parsedItems

        // When
        val result = mediaRepository.getHomeSections(page, limit)

        // Then
        assertTrue("Result should be success", result.isSuccess)
        assertTrue("Result should be ApiResult.Success", result is ApiResult.Success)

        val successResult = result as ApiResult.Success
        assertEquals("Should have 1 section", 1, successResult.result.sections.size)
        assertEquals("Section should have parsed items", parsedItems, successResult.result.sections[0].items)
        assertEquals("Status should match", "success", successResult.result.status)

        // Verify interactions
        coVerify(exactly = 1) { homeApiService.getHomeSections(page, limit) }
        verify(exactly = 1) { SectionParser.parse(mockItems, SectionContentType.PODCAST) }
    }

    @Test
    fun `getHomeSections returns error when API response body is null`() = runTest {
        // Given
        val page = 1
        val limit = 10
        val mockResponse = mockk<Response<SectionsResponse>>()

        every { mockResponse.body() } returns null
        coEvery { homeApiService.getHomeSections(page, limit) } returns mockResponse

        // When
        val result = mediaRepository.getHomeSections(page, limit)

        // Then
        assertFalse("Result should not be success", result.isSuccess)
        assertTrue("Result should be ApiResult.Error", result is ApiResult.Error)

        val errorResult = result as ApiResult.Error
        assertEquals("Error message should match", "Empty response body", errorResult.message)

        // Verify interactions
        coVerify(exactly = 1) { homeApiService.getHomeSections(page, limit) }
        verify(exactly = 0) { SectionParser.parse(any(), any()) }
    }

    @Test
    fun `search returns success when API call succeeds with valid search data`() = runTest {
        // Given
        val query = "test query"
        val mockItems = listOf<Any>() // Mock search items
        val mockSearchSection = SearchSection(
            name = "Search Results",
            type = null,
            sectionContentType = SectionContentType.EPISODE,
            order = "1",
            items = mockItems
        )
        val mockSearchResponse = SearchSectionsResponse(
            sections = listOf(mockSearchSection)
        )
        val mockResponse = mockk<Response<SearchSectionsResponse>>()
        val parsedSearchItems = listOf<Any>() // Mock parsed search items

        every { mockResponse.body() } returns mockSearchResponse
        coEvery { searchApiService.search(query) } returns mockResponse
        every {
            SearchSectionParser.parse(mockItems, SectionContentType.EPISODE)
        } returns parsedSearchItems

        // When
        val result = mediaRepository.search(query)

        // Then
        assertTrue("Result should be success", result.isSuccess)
        assertTrue("Result should be ApiResult.Success", result is ApiResult.Success)

        val successResult = result as ApiResult.Success
        assertEquals("Should have 1 search section", 1, successResult.result.sections.size)
        assertEquals("Section should have parsed search items", parsedSearchItems, successResult.result.sections[0].items)

        // Verify interactions
        coVerify(exactly = 1) { searchApiService.search(query) }
        verify(exactly = 1) { SearchSectionParser.parse(mockItems, SectionContentType.EPISODE) }
    }

    @Test
    fun `search returns error when API response body is null`() = runTest {
        // Given
        val query = "test query"
        val mockResponse = mockk<Response<SearchSectionsResponse>>()

        every { mockResponse.body() } returns null
        coEvery { searchApiService.search(query) } returns mockResponse

        // When
        val result = mediaRepository.search(query)

        // Then
        assertFalse("Result should not be success", result.isSuccess)
        assertTrue("Result should be ApiResult.Error", result is ApiResult.Error)

        val errorResult = result as ApiResult.Error
        assertEquals("Error message should match", "Empty response body", errorResult.message)

        // Verify interactions
        coVerify(exactly = 1) { searchApiService.search(query) }
        verify(exactly = 0) { SearchSectionParser.parse(any(), any()) }
    }

    @Test
    fun `getHomeSections handles multiple sections with different content types`() = runTest {
        val page = 1
        val limit = 20
        val mockItems1 = listOf<Any>()
        val mockItems2 = listOf<Any>()
        val mockSection1 = Section(
            name = "Podcasts",
            type = null,
            sectionContentType = SectionContentType.PODCAST,
            order = 1,
            items = mockItems1
        )
        val mockSection2 = Section(
            name = "Audio Books",
            type = null,
            sectionContentType = SectionContentType.AUDIO_BOOK,
            order = 2,
            items = mockItems2
        )
        val mockSectionsResponse = SectionsResponse(
            sections = listOf(mockSection1, mockSection2),
            pagination = null,
            status = "success"
        )
        val mockResponse = mockk<Response<SectionsResponse>>()
        val parsedItems1 = listOf<Any>()
        val parsedItems2 = listOf<Any>()

        every { mockResponse.body() } returns mockSectionsResponse
        coEvery { homeApiService.getHomeSections(page, limit) } returns mockResponse
        every { SectionParser.parse(mockItems1, SectionContentType.PODCAST) } returns parsedItems1
        every { SectionParser.parse(mockItems2, SectionContentType.AUDIO_BOOK) } returns parsedItems2

        // When
        val result = mediaRepository.getHomeSections(page, limit)

        // Then
        assertTrue("Result should be success", result.isSuccess)
        val successResult = result as ApiResult.Success
        assertEquals("Should have 2 sections", 2, successResult.result.sections.size)

        // Verify both sections were parsed correctly
        verify(exactly = 1) { SectionParser.parse(mockItems1, SectionContentType.PODCAST) }
        verify(exactly = 1) { SectionParser.parse(mockItems2, SectionContentType.AUDIO_BOOK) }
    }
}

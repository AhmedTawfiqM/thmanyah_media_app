package com.example.thmanyahmediaapp.data.repository

import com.example.thmanyahmediaapp.data.network.ApiResponse
import com.example.thmanyahmediaapp.domain.model.Section
import com.example.thmanyahmediaapp.domain.model.SectionsResponse
import com.example.thmanyahmediaapp.presentation.base.paging.BasePagingSource
import com.example.thmanyahmediaapp.presentation.base.paging.PaginationConfig
import kotlinx.coroutines.delay
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaPagingSource @Inject constructor(
    private val mediaRepository: MediaRepository,
) : BasePagingSource<Section>() {

    override suspend fun loadData(params: LoadParams<Int>): LoadResult<Int, Section> {
        val page = params.key ?: 1
        val response = mediaRepository.getHomeSections(
            page = page,
            limit = PaginationConfig.PAGE_SIZE,
        )

        delay(1000)

        return if (response.isSuccess) {
            val successResponse = response as ApiResponse.Success<SectionsResponse>
            val sectionsResponse = successResponse.result
            val sections = sectionsResponse.sections
            val pagination = sectionsResponse.pagination

            val hasNextPage = pagination?.totalPages != null && page < pagination.totalPages

            LoadResult.Page(
                data = sections,
                prevKey = if (page == 1) null else page - 1,
                nextKey = if (hasNextPage && sections.isNotEmpty()) page + 1 else null
            )
        } else {
            throw Exception("API Error")
        }
    }
}

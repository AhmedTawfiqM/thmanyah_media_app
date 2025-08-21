package com.example.thmanyahmediaapp.data.repository

import com.example.thmanyahmediaapp.data.network.ApiResponse
import com.example.thmanyahmediaapp.domain.model.Section
import com.example.thmanyahmediaapp.domain.model.SectionsResponse
import com.example.thmanyahmediaapp.presentation.base.paging.BasePagingSource
import com.example.thmanyahmediaapp.presentation.base.paging.PaginationConfig
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

        // Handle the response properly
        val result = if (response.isSuccess) {
            (response as ApiResponse.Success<SectionsResponse> ).result.sections
        } else {
            throw Exception("API Error")
        }

        return LoadResult.Page(
            data = result,
            prevKey = if (page == 1) null else page - 1,
            nextKey = if (result.isEmpty()) null else page + 1
        )
    }
}

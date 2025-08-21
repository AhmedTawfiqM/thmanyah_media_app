package com.example.thmanyahmediaapp.domain

import com.example.thmanyahmediaapp.data.network.ApiResult
import com.example.thmanyahmediaapp.domain.model.SectionsResponse

interface IMediaRepository {
    suspend fun getHomeSections(
        page: Int,
        limit: Int,
    ): ApiResult<SectionsResponse>

    suspend fun search(query: String): ApiResult<SectionsResponse>
}
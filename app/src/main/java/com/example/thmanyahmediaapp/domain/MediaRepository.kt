package com.example.thmanyahmediaapp.domain

import com.example.thmanyahmediaapp.data.network.ApiResponse
import com.example.thmanyahmediaapp.domain.model.SectionsResponse

interface IMediaRepository {
    suspend fun getHomeSections(): ApiResponse<SectionsResponse>

    suspend fun search(query: String, page: Int = 1, limit: Int = 20): ApiResponse<*>
}
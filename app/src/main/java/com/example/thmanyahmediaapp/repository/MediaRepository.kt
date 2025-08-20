package com.example.thmanyahmediaapp.repository

import com.example.thmanyahmediaapp.network.ApiResponse
import com.example.thmanyahmediaapp.network.HomeApiService
import com.example.thmanyahmediaapp.network.SearchApiService
import com.example.thmanyahmediaapp.network.model.SectionsResponse
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepository @Inject constructor(
    private val homeApi: HomeApiService,
    private val searchApi: SearchApiService
) {

    suspend fun getHomeSections(): ApiResponse<SectionsResponse> {
        val response = homeApi.getHomeSections()
        return response.body()?.let { body ->
            ApiResponse.Success(body)
        } ?: ApiResponse.Error("Empty response body")
    }

    suspend fun search(query: String, page: Int = 1, limit: Int = 20): ApiResponse<*> {
        val response = searchApi.search(query, page, limit)
        return response.body()?.let { body ->
            ApiResponse.Success(body)
        } ?: ApiResponse.Error("Empty response body")
    }
}

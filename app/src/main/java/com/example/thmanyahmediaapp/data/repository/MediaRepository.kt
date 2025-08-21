package com.example.thmanyahmediaapp.data.repository

import com.example.thmanyahmediaapp.data.SectionParser
import com.example.thmanyahmediaapp.domain.IMediaRepository
import com.example.thmanyahmediaapp.domain.model.SectionsResponse
import com.example.thmanyahmediaapp.data.network.ApiResponse
import com.example.thmanyahmediaapp.data.network.HomeApiService
import com.example.thmanyahmediaapp.data.network.SearchApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepository @Inject constructor(
    private val homeApi: HomeApiService,
    private val searchApi: SearchApiService
) : IMediaRepository {

    override suspend fun getHomeSections(
        page: Int,
        limit: Int,
    ): ApiResponse<SectionsResponse> {
        val response = homeApi.getHomeSections(page, limit)
        return response.body()?.let { body ->
            val parsedSections = body.sections.map { section ->
                section.copy(items = SectionParser.parse(section.items, section.contentType))
            }
            ApiResponse.Success(body.copy(sections = parsedSections))
        } ?: ApiResponse.Error("Empty response body")
    }

    override suspend fun search(query: String, page: Int, limit: Int): ApiResponse<*> {
        val response = searchApi.search(query, page, limit)
        return response.body()?.let { body ->
            ApiResponse.Success(body)
        } ?: ApiResponse.Error("Empty response body")
    }
}

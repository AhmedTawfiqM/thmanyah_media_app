package com.example.thmanyahmediaapp.repository

import com.example.thmanyahmediaapp.network.ApiResponse
import com.example.thmanyahmediaapp.network.HomeApiService
import com.example.thmanyahmediaapp.network.SearchApiService
import com.example.thmanyahmediaapp.network.model.*
import com.example.thmanyahmediaapp.utils.ContentParser
import com.google.gson.Gson
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepository @Inject constructor(
    private val homeApi: HomeApiService,
    private val searchApi: SearchApiService
) {
    private val gson = Gson()

    suspend fun getHomeSections(): ApiResponse<SectionsResponse> {
        val response = homeApi.getHomeSections()
        return response.body()?.let { body ->
            // Parse content for each section and return typed sections
            val parsedSections = body.sections.map { section ->
                section.copy(content = ContentParser.parseContentForSection(section.content, section.contentType))
            }
            ApiResponse.Success(body.copy(sections = parsedSections))
        } ?: ApiResponse.Error("Empty response body")
    }

    suspend fun search(query: String, page: Int = 1, limit: Int = 20): ApiResponse<*> {
        val response = searchApi.search(query, page, limit)
        return response.body()?.let { body ->
            ApiResponse.Success(body)
        } ?: ApiResponse.Error("Empty response body")
    }


}

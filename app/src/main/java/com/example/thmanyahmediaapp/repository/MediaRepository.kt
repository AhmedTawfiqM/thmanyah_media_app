package com.example.thmanyahmediaapp.repository

import com.example.thmanyahmediaapp.network.HomeApiService
import com.example.thmanyahmediaapp.network.SearchApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MediaRepository @Inject constructor(
    private val homeApi: HomeApiService,
    private val searchApi: SearchApiService
) {

    suspend fun getHomeSections() = try {
        val response = homeApi.getHomeSections()
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Failed to get home sections: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }

    // Search API methods
    suspend fun search(query: String, page: Int = 1, limit: Int = 20) = try {
        val response = searchApi.search(query, page, limit)
        if (response.isSuccessful) {
            Result.success(response.body()!!)
        } else {
            Result.failure(Exception("Failed to search: ${response.code()}"))
        }
    } catch (e: Exception) {
        Result.failure(e)
    }
}

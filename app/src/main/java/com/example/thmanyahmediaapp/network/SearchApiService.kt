package com.example.thmanyahmediaapp.network

import com.example.thmanyahmediaapp.network.model.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search")
    suspend fun search(
        @Query("query") query: String,
        @Query("page") page: Int = 1,
        @Query("limit") limit: Int = 20
    ): Response<SearchResponse>
}
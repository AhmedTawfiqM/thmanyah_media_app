package com.example.thmanyahmediaapp.data.network

import com.example.thmanyahmediaapp.domain.model.search.SearchSectionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchApiService {

    @GET("search")
    suspend fun search(
        @Query("query") query: String,
    ): Response<SearchSectionsResponse>
}
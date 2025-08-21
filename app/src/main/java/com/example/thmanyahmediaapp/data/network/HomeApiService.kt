package com.example.thmanyahmediaapp.data.network

import com.example.thmanyahmediaapp.data.model.SectionsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface HomeApiService {

    @GET("home_sections")
    suspend fun getHomeSections(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
    ): Response<SectionsResponse>
}
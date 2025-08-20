package com.example.thmanyahmediaapp.data.network

import com.example.thmanyahmediaapp.domain.model.SectionsResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {

    @GET("home_sections")
    suspend fun getHomeSections(): Response<SectionsResponse>
}
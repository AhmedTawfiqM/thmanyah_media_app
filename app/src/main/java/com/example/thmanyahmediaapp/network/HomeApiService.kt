package com.example.thmanyahmediaapp.network

import com.example.thmanyahmediaapp.network.model.SectionsResponse
import retrofit2.Response
import retrofit2.http.GET

interface HomeApiService {

    @GET("home_sections")
    suspend fun getHomeSections(): Response<SectionsResponse>
}
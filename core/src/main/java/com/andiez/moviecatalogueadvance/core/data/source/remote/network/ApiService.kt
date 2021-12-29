package com.andiez.moviecatalogueadvance.core.data.source.remote.network

import com.andiez.moviecatalogueadvance.core.data.source.remote.response.ListMovieResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("/movie/now_playing")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = "",
        @Query("page") page: Int = 1
    ): ListMovieResponse
}
package com.andiez.moviecatalogueadvance.core.data.source.remote.network

import com.andiez.moviecatalogueadvance.core.data.source.remote.response.ListMovieResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.ListTvResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = "",
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = "",
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("tv/airing_today")
    suspend fun getTvShow(
        @Query("api_key") apiKey: String = "",
        @Query("page") page: Int = 1
    ): ListTvResponse
}
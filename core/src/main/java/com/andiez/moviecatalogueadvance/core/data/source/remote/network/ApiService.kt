package com.andiez.moviecatalogueadvance.core.data.source.remote.network

import com.andiez.moviecatalogueadvance.core.BuildConfig
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.ListCastResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.ListMovieResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.ListTvResponse
import com.andiez.moviecatalogueadvance.core.data.source.remote.response.MovieDetailResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {
    @GET("movie/now_playing")
    suspend fun getMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("movie/popular")
    suspend fun getPopularMovie(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): ListMovieResponse

    @GET("tv/airing_today")
    suspend fun getTvShow(
        @Query("api_key") apiKey: String = BuildConfig.API_KEY,
        @Query("page") page: Int = 1
    ): ListTvResponse

    @GET("movie/{movie_id}")
    suspend fun getDetailMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): MovieDetailResponse

    @GET("movie/{movie_id}/credits")
    suspend fun getCastMovie(
        @Path("movie_id") id: Int,
        @Query("api_key") apiKey: String = BuildConfig.API_KEY
    ): ListCastResponse
}
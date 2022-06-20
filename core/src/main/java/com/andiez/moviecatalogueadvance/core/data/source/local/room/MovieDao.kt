package com.andiez.moviecatalogueadvance.core.data.source.local.room

import androidx.room.*
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.*
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity")
    fun getMovies(): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentity")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM moviedetailentity WHERE id = :id")
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity>

    @Query("SELECT * FROM tvshowdetailentity WHERE id = :id")
    fun getTvDetail(id: Int): Flow<TvShowDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTvShows(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetailMovie(detailMovieEntity: MovieDetailEntity)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetailTv(detailTvShowEntity: TvShowDetailEntity)

    @Query("UPDATE moviedetailentity SET isFavorite = :state WHERE id = :id")
    fun updateMovieDetailFavorite(id: Int, state: Boolean)

    @Query("SELECT * FROM movieentity JOIN moviedetailentity ON moviedetailentity.isFavorite = 1 AND movieentity.id = moviedetailentity.id")
    fun getMoviesFavorite(): Flow<List<MovieEntity>>

    @Query("UPDATE tvshowdetailentity SET isFavorite = :state WHERE id = :id")
    fun updateTvDetailFavorite(id: Int, state: Boolean)

    @Query("SELECT * FROM tvshowentity JOIN TvShowDetailEntity ON TvShowDetailEntity.isFavorite = 1 AND tvshowentity.id = TvShowDetailEntity.id")
    fun getTvShowsFavorite(): Flow<List<TvShowEntity>>
}
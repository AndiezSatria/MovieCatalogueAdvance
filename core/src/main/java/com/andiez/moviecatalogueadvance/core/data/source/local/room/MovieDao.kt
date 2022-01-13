package com.andiez.moviecatalogueadvance.core.data.source.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieDetailEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.ShowCategory
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.TvShowEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface MovieDao {
    @Query("SELECT * FROM movieentity WHERE category = :category")
    fun getPopularMovies(category: String = ShowCategory.Popular.name): Flow<List<MovieEntity>>

    @Query("SELECT * FROM movieentity WHERE category = :categoryPlaying OR category = :categoryPopular ")
    fun getMovies(
        categoryPlaying: String = ShowCategory.NowPlaying.name,
        categoryPopular: String = ShowCategory.Popular.name
    ): Flow<List<MovieEntity>>

    @Query("SELECT * FROM tvshowentity")
    fun getTvShows(): Flow<List<TvShowEntity>>

    @Query("SELECT * FROM moviedetailentity WHERE id = :id")
    fun getMovieDetail(id: Int): Flow<MovieDetailEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertMovies(movies: List<MovieEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertTvShows(tvShow: List<TvShowEntity>)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertDetailMovie(detailMovieEntity: MovieDetailEntity)
}
package com.andiez.moviecatalogueadvance.core.data.source.local.realm

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.GenreEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.MovieEntity
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.ShowCategory
import com.andiez.moviecatalogueadvance.core.data.source.local.entity.TvShowEntity
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class MovieDao @Inject constructor(private val realm: Realm) : IMovieDao {
    override fun getGenres(): Flow<List<GenreEntity>> {
        return realm.where(GenreEntity::class.java).findAllAsync().toFlow()
    }

    override suspend fun insertGenres(genres: List<GenreEntity>) {
        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.copyToRealmOrUpdate(genres)
        }
    }

    override fun getMovies(): Flow<List<MovieEntity>> {
        return realm.where(MovieEntity::class.java)
            .equalTo("category", ShowCategory.NowPlaying.toString())
            .or()
            .equalTo("category", ShowCategory.Popular.toString())
            .findAllAsync()
            .toFlow()
    }

    override suspend fun insertMovies(movies: List<MovieEntity>) {
        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.copyToRealmOrUpdate(movies)
        }
    }

    override fun getPopularMovies(): Flow<List<MovieEntity>> {
        return realm.where(MovieEntity::class.java)
            .equalTo("category", ShowCategory.Popular.toString())
            .limit(10)
            .findAllAsync()
            .toFlow()
    }

    override fun getTvShows(): Flow<List<TvShowEntity>> {
        return realm.where(TvShowEntity::class.java)
            .equalTo("category", ShowCategory.NowPlaying.toString())
            .or()
            .equalTo("category", ShowCategory.Popular.toString())
            .findAllAsync()
            .toFlow()
    }

    override suspend fun insertTvShows(tvShows: List<TvShowEntity>) {
        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
            transaction.copyToRealmOrUpdate(tvShows)
        }
    }
}
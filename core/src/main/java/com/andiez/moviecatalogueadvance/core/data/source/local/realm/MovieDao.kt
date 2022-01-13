package com.andiez.moviecatalogueadvance.core.data.source.local.realm

import com.andiez.moviecatalogueadvance.core.data.source.local.entity.*
import io.realm.ImportFlag
import io.realm.Realm
import io.realm.kotlin.executeTransactionAwait
import io.realm.kotlin.toFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class MovieDao @Inject constructor(private val realm: Realm) : IMovieDao {
//    override fun getGenres(): Flow<List<GenreEntity>> {
//        return realm.where(GenreEntity::class.java).findAllAsync().toFlow()
//    }
//
//    override suspend fun insertGenres(genres: List<GenreEntity>) {
//        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
//            transaction.copyToRealmOrUpdate(genres)
//        }
//    }
//
//    override fun getMovies(): Flow<List<MovieEntity>> {
//        return realm.where(MovieEntity::class.java)
//            .equalTo("category", ShowCategory.NowPlaying.toString())
//            .or()
//            .equalTo("category", ShowCategory.Popular.toString())
//            .findAllAsync()
//            .toFlow()
//    }
//
//    override suspend fun insertMovies(movies: List<MovieEntity>) {
//        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
//            transaction.copyToRealmOrUpdate(movies, ImportFlag.CHECK_SAME_VALUES_BEFORE_SET)
//        }
//    }
//
//    override fun getPopularMovies(): Flow<List<MovieEntity>> {
//        return realm.where(MovieEntity::class.java)
//            .equalTo("category", ShowCategory.Popular.toString())
//            .limit(10)
//            .findAllAsync()
//            .toFlow()
//    }
//
//    override fun getTvShows(): Flow<List<TvShowEntity>> {
//        return realm.where(TvShowEntity::class.java)
//            .equalTo("category", ShowCategory.NowPlaying.toString())
//            .or()
//            .equalTo("category", ShowCategory.Popular.toString())
//            .findAllAsync()
//            .toFlow()
//    }
//
//    override suspend fun insertTvShows(tvShows: List<TvShowEntity>) {
//        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
//            transaction.copyToRealmOrUpdate(tvShows, ImportFlag.CHECK_SAME_VALUES_BEFORE_SET)
//        }
//    }
//
//    override fun getDetailMovie(id: Int): Flow<MovieDetailEntity?> {
//        return realm.where(MovieDetailEntity::class.java)
//            .equalTo("id", id)
//            .findFirstAsync()
//            .toFlow()
//    }
//
//    override suspend fun insertDetailMovie(detailMovie: MovieDetailEntity) {
//        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
//            transaction.copyToRealmOrUpdate(detailMovie)
//        }
//    }
//
//    override suspend fun updateMovie(movie: MovieEntity, detailMovie: MovieDetailEntity) {
//        realm.executeTransactionAwait(Dispatchers.IO) { transaction ->
//            transaction.copyToRealmOrUpdate(detailMovie)
//            transaction.copyToRealmOrUpdate(movie)
//        }
//    }
}
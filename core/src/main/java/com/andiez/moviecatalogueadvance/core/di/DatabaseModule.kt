package com.andiez.moviecatalogueadvance.core.di

import com.andiez.moviecatalogueadvance.core.data.source.local.realm.IMovieDao
import com.andiez.moviecatalogueadvance.core.data.source.local.realm.MovieDao
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class DatabaseModule {

    @Binds
    @Singleton
    abstract fun provideMovieDao(movieDao: MovieDao): IMovieDao
}
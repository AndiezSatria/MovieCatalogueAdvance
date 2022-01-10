package com.andiez.moviecatalogueadvance.core.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.realm.Realm
import io.realm.RealmConfiguration
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RealmModule {

    @Singleton
    @Provides
    fun provideRealm(): Realm = Realm.getDefaultInstance()
}
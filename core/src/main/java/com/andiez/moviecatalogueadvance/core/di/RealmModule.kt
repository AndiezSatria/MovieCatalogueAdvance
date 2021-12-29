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
    fun provideRealmConfig(): RealmConfiguration = RealmConfiguration.Builder()
        .schemaVersion(1L)
        .build()

    @Singleton
    @Provides
    fun provideRealm(configuration: RealmConfiguration): Realm = Realm.getInstance(configuration)
}
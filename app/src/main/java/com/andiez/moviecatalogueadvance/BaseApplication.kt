package com.andiez.moviecatalogueadvance

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import io.realm.Realm
import io.realm.RealmConfiguration

@HiltAndroidApp
class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        val configuration = RealmConfiguration.Builder()
            .deleteRealmIfMigrationNeeded()
            .schemaVersion(3L)
            .build()
        Realm.setDefaultConfiguration(configuration)
    }
}
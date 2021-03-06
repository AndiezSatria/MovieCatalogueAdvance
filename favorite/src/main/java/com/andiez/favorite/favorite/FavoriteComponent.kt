package com.andiez.favorite.favorite

import android.content.Context
import com.andiez.favorite.FavoriteFragment
import com.andiez.moviecatalogueadvance.di.UseCaseModuleDependencies
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [UseCaseModuleDependencies::class])
interface FavoriteComponent {

    fun inject(fragment: FavoriteFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(mapsModuleDependencies: UseCaseModuleDependencies): Builder
        fun build(): FavoriteComponent
    }
}
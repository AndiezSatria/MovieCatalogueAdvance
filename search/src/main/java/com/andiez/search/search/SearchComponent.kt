package com.andiez.search.search

import android.content.Context
import com.andiez.moviecatalogueadvance.di.UseCaseModuleDependencies
import com.andiez.search.SearchFragment
import dagger.BindsInstance
import dagger.Component

@Component(dependencies = [UseCaseModuleDependencies::class])
interface SearchComponent {
    fun inject(fragment: SearchFragment)

    @Component.Builder
    interface Builder {
        fun context(@BindsInstance context: Context): Builder
        fun appDependencies(useCaseModuleDependencies: UseCaseModuleDependencies): Builder
        fun build(): SearchComponent
    }
}
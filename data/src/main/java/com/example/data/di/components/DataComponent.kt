package com.example.data.di.components

import android.content.Context
import com.example.data.di.modules.NetModule
import com.example.data.di.modules.PersistenceModule
import com.example.data.di.modules.RepositoryModule
import com.zero.musichunter.domain.repository.RepoRepository
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(NetModule::class), (PersistenceModule::class), RepositoryModule::class])
interface DataComponent {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance context: Context): DataComponent
    }

    // Exposed to sub-graphs
    fun provideRepoRepository(): RepoRepository

}
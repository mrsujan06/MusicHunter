package com.example.data.di.modules

import com.example.data.net.MusicApiService
import com.example.data.persistence.database.MusicDatabase
import com.example.data.repository.RepoRepositoryDataRepository
import com.zero.musichunter.domain.repository.RepoRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    open fun provideMusicRepository(
        musicApiService: MusicApiService,
        database: MusicDatabase
    ): RepoRepository = RepoRepositoryDataRepository(musicApiService, database)
}
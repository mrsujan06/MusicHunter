package com.example.data.di.modules

import com.example.data.di.providers.NetworkChecker
import com.example.data.mapper.RepoMapper
import com.example.data.net.api.MusicApiService
import com.example.data.persistence.processor.RepoProcessor
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
        repoProcessor: RepoProcessor,
        repoMapper: RepoMapper,
        networkChecker: NetworkChecker
    ): RepoRepository =
        RepoRepositoryDataRepository(musicApiService, repoProcessor, repoMapper, networkChecker)

}
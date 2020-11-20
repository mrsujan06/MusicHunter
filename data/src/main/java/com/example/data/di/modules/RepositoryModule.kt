package com.example.data.di.modules

import com.example.data.di.providers.NetworkChecker
import com.example.data.mapper.RepoMapper
import com.example.data.net.api.MusicApiService
import com.example.data.persistence.processor.ClassicProcessor
import com.example.data.persistence.processor.PopProcessor
import com.example.data.persistence.processor.RockProcessor
import com.example.data.repository.ClassicRepositoryImp
import com.example.data.repository.PopRepositoryImp
import com.example.data.repository.RockRepositoryImp
import com.zero.musichunter.domain.repository.ClassicRepository
import com.zero.musichunter.domain.repository.PopRepository
import com.zero.musichunter.domain.repository.RockRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
open class RepositoryModule {

    @Provides
    @Singleton
    open fun provideClassicRepository(
        musicApiService: MusicApiService,
        classicProcessor: ClassicProcessor,
        repoMapper: RepoMapper,
        networkChecker: NetworkChecker
    ): ClassicRepository =
        ClassicRepositoryImp(musicApiService, classicProcessor, repoMapper, networkChecker)

    @Provides
    @Singleton
    open fun providePopRepository(
        musicApiService: MusicApiService,
        popProcessor: PopProcessor,
        repoMapper: RepoMapper,
        networkChecker: NetworkChecker
    ): PopRepository =
        PopRepositoryImp(musicApiService, popProcessor, repoMapper, networkChecker)


    @Provides
    @Singleton
    open fun provideRockRepository(
        musicApiService: MusicApiService,
        rockProcessor: RockProcessor,
        repoMapper: RepoMapper,
        networkChecker: NetworkChecker
    ): RockRepository =
        RockRepositoryImp(musicApiService, rockProcessor, repoMapper, networkChecker)

}
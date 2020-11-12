package com.zero.musichunter.di.modules

import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.domain.repository.RepoRepository
import com.zero.musichunter.domain.usecases.GetRockListRepo
import com.zero.musichunter.ui.fragment.rock.RockMusicViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class RockMusicModule {

    @Provides
    @PerApplication
    fun provideGetRockListRepo(repoRepository: RepoRepository): GetRockListRepo =
        GetRockListRepo(repoRepository)

    @Provides
    @PerApplication
    fun provideRockMusicViewModelFactory(getRockListRepo: GetRockListRepo): RockMusicViewModelFactory =
        RockMusicViewModelFactory(getRockListRepo)

}
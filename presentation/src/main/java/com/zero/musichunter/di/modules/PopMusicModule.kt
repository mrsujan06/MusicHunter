package com.zero.musichunter.di.modules

import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.domain.repository.RepoRepository
import com.zero.musichunter.domain.usecases.GetPopListRepo
import com.zero.musichunter.ui.fragment.pop.PopMusicViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class PopMusicModule {

    @Provides
    @PerApplication
    internal fun provideGetListPopRepo(repository: RepoRepository): GetPopListRepo{
        return GetPopListRepo(repository)
    }

    @Provides
    @PerApplication
    internal fun provideClassicViewModeFactory(getPopListRepo: GetPopListRepo): PopMusicViewModelFactory{
        return PopMusicViewModelFactory(getPopListRepo)
    }
}
package com.zero.musichunter.di.modules

import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.domain.repository.RepoRepository
import com.zero.musichunter.domain.usecases.GetClassicListRepo
import com.zero.musichunter.ui.fragment.classic.ClassicMusicViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ClassicMusicModule {

    @Provides
    @PerApplication
    internal fun provideGetClassicListRepo(repository: RepoRepository): GetClassicListRepo =
        GetClassicListRepo(repository)

    @Provides
    @PerApplication
    internal fun provideClassicViewModelFactory(getClassicListRepo: GetClassicListRepo): ClassicMusicViewModelFactory =
        ClassicMusicViewModelFactory(getClassicListRepo)
}
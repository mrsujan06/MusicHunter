package com.zero.musichunter.di.modules

import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.domain.usecases.GetClassicListRepo
import com.zero.musichunter.domain.usecases.GetPopListRepo
import com.zero.musichunter.domain.usecases.GetRockListRepo
import com.zero.musichunter.ui.fragment.classic.ClassicMusicViewModelFactory
import com.zero.musichunter.ui.fragment.pop.PopMusicViewModelFactory
import com.zero.musichunter.ui.fragment.rock.RockMusicViewModelFactory
import dagger.Module
import dagger.Provides

@Module
class ViewModelFactoryModule {

    @Provides
    @PerApplication
    internal fun provideClassicViewModelFactory(getClassicListRepo: GetClassicListRepo): ClassicMusicViewModelFactory =
        ClassicMusicViewModelFactory(getClassicListRepo)

    @Provides
    @PerApplication
    internal fun provideClassicViewModeFactory(getPopListRepo: GetPopListRepo): PopMusicViewModelFactory {
        return PopMusicViewModelFactory(getPopListRepo)
    }

    @Provides
    @PerApplication
    fun provideRockMusicViewModelFactory(getRockListRepo: GetRockListRepo): RockMusicViewModelFactory =
        RockMusicViewModelFactory(getRockListRepo)
}
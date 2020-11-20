package com.zero.musichunter.di.modules

import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.domain.repository.ClassicRepository
import com.zero.musichunter.domain.repository.PopRepository
import com.zero.musichunter.domain.repository.RockRepository
import com.zero.musichunter.domain.usecases.GetClassicListRepo
import com.zero.musichunter.domain.usecases.GetPopListRepo
import com.zero.musichunter.domain.usecases.GetRockListRepo
import com.zero.musichunter.domain.usecases.base.Logger
import com.zero.musichunter.domain.usecases.base.UseCaseScheduler
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {

    @Provides
    @PerApplication
    internal fun provideGetClassicListRepo(
        classicRepository: ClassicRepository,
        useCaseScheduler: UseCaseScheduler,
        logger: Logger
    ): GetClassicListRepo =
        GetClassicListRepo(classicRepository, useCaseScheduler, logger)

    @Provides
    @PerApplication
    internal fun provideGetListPopRepo(
        popRepository: PopRepository,
        useCaseScheduler: UseCaseScheduler,
        logger: Logger
    ): GetPopListRepo {
        return GetPopListRepo(popRepository, useCaseScheduler, logger)
    }

    @Provides
    @PerApplication
    fun provideGetRockListRepo(
        rockRepository: RockRepository,
        useCaseScheduler: UseCaseScheduler,
        logger: Logger
    ): GetRockListRepo =
        GetRockListRepo(rockRepository, useCaseScheduler, logger)

}
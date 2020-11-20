package com.zero.musichunter.di.modules

import android.app.Application
import android.content.Context
import com.example.data.utils.TimberWrapper
import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.domain.usecases.base.Logger
import dagger.Module
import dagger.Provides

@Module
class ApplicationModule {

    @Provides
    @PerApplication
    internal fun provideContext(application: Application): Context = application.baseContext


    @Provides
    @PerApplication
    internal fun provideLogger(): Logger = object : Logger {
        override fun log(message: () -> String) {
            TimberWrapper.d(message)
        }

        override fun logError(throwable: () -> Throwable) {
            TimberWrapper.e(throwable)
        }
    }
}
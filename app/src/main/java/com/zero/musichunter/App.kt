package com.zero.musichunter

import android.app.Application
import com.zero.musichunter.dagger.AppComponent
import com.zero.musichunter.dagger.AppModule
import com.zero.musichunter.dagger.DaggerAppComponent
import timber.log.Timber

class App : Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = initDagger()
        Timber.plant(Timber.DebugTree())
    }

    private fun initDagger(): AppComponent =
        DaggerAppComponent.builder().appModule(AppModule(this))
            .build()

}
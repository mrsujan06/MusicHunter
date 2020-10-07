package com.zero.musichunter.testDagger

import com.zero.musichunter.dagger.AppComponent
import com.zero.musichunter.dagger.AppModule
import com.zero.musichunter.viewModelTest.ClassicViewModelTest
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface TestAppComponent : AppComponent{
    fun inject(classicViewModelTest: ClassicViewModelTest)

}
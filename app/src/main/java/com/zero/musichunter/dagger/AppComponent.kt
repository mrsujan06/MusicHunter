package com.zero.musichunter.dagger

import com.zero.musichunter.ui.fragment.classic.ClassicFragment
import com.zero.musichunter.ui.fragment.pop.PopFragment
import com.zero.musichunter.ui.fragment.rock.RockFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [AppModule::class])
interface AppComponent {
    fun inject(classicFragment: ClassicFragment)
    fun inject(popFragment: PopFragment)
    fun inject(rockFragment: RockFragment)
}
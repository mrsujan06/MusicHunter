package com.zero.musichunter.di.components

import android.app.Activity
import com.zero.musichunter.di.PerActivity
import com.zero.musichunter.di.modules.ActivityModule
import com.zero.musichunter.ui.fragment.classic.ClassicMusicFragment
import com.zero.musichunter.ui.fragment.pop.PopMusicFragment
import com.zero.musichunter.ui.fragment.rock.RockMusicFragment
import dagger.BindsInstance
import dagger.Subcomponent

@PerActivity
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {

    @Subcomponent.Factory
    interface Factory {
        fun create(@BindsInstance activity: Activity): ActivityComponent
    }

    //region Inject
    fun inject(musicFragment: ClassicMusicFragment)

    fun inject(musicFragment: PopMusicFragment)

    fun inject(musicFragment: RockMusicFragment)
    //endregion

}
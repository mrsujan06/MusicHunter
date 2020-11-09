package com.zero.musichunter.di.components

import android.app.Activity
import com.zero.musichunter.di.PerActivity
import com.zero.musichunter.di.modules.ActivityModule
import com.zero.musichunter.ui.fragment.classic.ClassicFragment
import com.zero.musichunter.ui.fragment.pop.PopFragment
import com.zero.musichunter.ui.fragment.rock.RockFragment
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
    fun inject(fragment: ClassicFragment)

    fun inject(fragment: PopFragment)

    fun inject(fragment: RockFragment)
    //endregion

}
package com.zero.musichunter.di.components

import android.app.Application
import com.example.data.di.components.DataComponent
import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.di.modules.*
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    dependencies = [(DataComponent::class)],
    modules = [
        (ApplicationModule::class),
        (UseCaseModule::class),
        (ClassicMusicModule::class),
        (PopMusicModule::class),
        (RockMusicModule::class)
    ]
)
interface ApplicationComponent {
    @Component.Factory
    interface Factory {
        fun create(
            @BindsInstance application: Application,
            dataComponent: DataComponent
        ): ApplicationComponent
    }

    fun activityComponent(): ActivityComponent.Factory
}
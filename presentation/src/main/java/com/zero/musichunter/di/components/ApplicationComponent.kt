package com.zero.musichunter.di.components

import android.app.Application
import com.example.data.di.components.DataComponent
import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.di.modules.ApplicationModule
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    dependencies = [(DataComponent::class)],
    modules = [(ApplicationModule::class)]
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
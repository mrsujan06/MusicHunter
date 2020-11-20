package com.zero.musichunter.di.components

import android.app.Application
import com.example.data.di.components.DataComponent
import com.zero.musichunter.di.PerApplication
import com.zero.musichunter.di.modules.ApplicationModule
import com.zero.musichunter.di.modules.SchedulerModule
import com.zero.musichunter.di.modules.UseCaseModule
import com.zero.musichunter.di.modules.ViewModelFactoryModule
import dagger.BindsInstance
import dagger.Component

@PerApplication
@Component(
    dependencies = [(DataComponent::class)],
    modules = [
        (ApplicationModule::class),
        (SchedulerModule::class),
        (UseCaseModule::class),
        (ViewModelFactoryModule::class),
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
package com.raul.revolutcodetask.dagger.component

import android.app.Application
import com.raul.revolutcodetask.RatesApp
import com.raul.revolutcodetask.dagger.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidInjectionModule::class,
        ActivityBuilder::class,
        AppModule::class,
        NetworkModule::class,
        ViewModelFactoryModule::class,
        RatesSingletonModule::class,
        AdapterModule::class
    ]
)
interface ApplicationComponent : AndroidInjector<RatesApp> {
    fun inject(app: Application)

    override fun inject(instance: RatesApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: RatesApp): Builder

        fun build(): ApplicationComponent
    }
}

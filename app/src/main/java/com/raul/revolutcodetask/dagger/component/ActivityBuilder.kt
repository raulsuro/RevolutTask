package com.raul.revolutcodetask.dagger.component

import com.raul.revolutcodetask.dagger.module.MainActivityModule
import com.raul.revolutcodetask.dagger.scope.ActivityScope
import com.raul.revolutcodetask.presentation.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {

    @ActivityScope
    @ContributesAndroidInjector(modules = [MainActivityModule::class])
    abstract fun bindMainActivity(): MainActivity

}
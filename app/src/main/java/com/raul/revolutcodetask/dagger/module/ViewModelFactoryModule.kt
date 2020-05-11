package com.raul.revolutcodetask.dagger.module

import androidx.lifecycle.ViewModelProvider
import com.raul.revolutcodetask.presentation.interactor.factory.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}
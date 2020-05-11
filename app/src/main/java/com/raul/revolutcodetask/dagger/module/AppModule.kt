package com.raul.revolutcodetask.dagger.module

import android.app.Application
import com.raul.revolutcodetask.RatesApp
import dagger.Module

@Module
class AppModule(application: RatesApp) {
    private val application: RatesApp = application

    fun providesApplication(): Application {
        return application
    }

}
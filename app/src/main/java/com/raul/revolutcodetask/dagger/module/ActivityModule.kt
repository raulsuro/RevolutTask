package com.raul.revolutcodetask.dagger.module

import android.app.Activity
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.raul.revolutcodetask.dagger.scope.ActivityScope
import dagger.Module
import dagger.Provides

@Module
abstract class ActivityModule<T : AppCompatActivity> {

    @Provides
    @ActivityScope
    fun provideContext(activity: T): Context {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideActivity(activity: T): Activity {
        return activity
    }

    @Provides
    @ActivityScope
    fun provideAppCompatActivity(activity: T): AppCompatActivity {
        return activity
    }

}
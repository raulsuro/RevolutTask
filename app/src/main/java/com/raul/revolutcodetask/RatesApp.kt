package com.raul.revolutcodetask

import android.app.Activity
import android.app.Application
import coil.ImageLoader
import coil.ImageLoaderFactory
import coil.decode.SvgDecoder
import com.raul.revolutcodetask.dagger.component.ApplicationComponent
import com.raul.revolutcodetask.dagger.component.DaggerApplicationComponent
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import javax.inject.Inject

class RatesApp : Application(), HasActivityInjector, ImageLoaderFactory {

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    private val component: ApplicationComponent by lazy {
        DaggerApplicationComponent
            .builder()
            .application(this)
            .build()
    }

    override fun onCreate() {
        super.onCreate()
        component.inject(this)
    }

    override fun activityInjector(): AndroidInjector<Activity> = androidInjector

    override fun newImageLoader(): ImageLoader {
        return ImageLoader.Builder(this)
            .crossfade(true)
            .componentRegistry {
                add(SvgDecoder(this@RatesApp))
            }
            .build()
    }
}
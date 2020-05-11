package com.raul.revolutcodetask.dagger.module

import com.raul.revolutcodetask.presentation.adapter.RatesListAdapter
import com.raul.revolutcodetask.presentation.adapter.listener.RateItemClickListener
import com.raul.revolutcodetask.presentation.adapter.listener.RateItemClickListenerImpl
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class AdapterModule {

    @Provides
    @Singleton
    fun provideRateItemClickListener(listener: RateItemClickListenerImpl): RateItemClickListener =
        listener

    @Provides
    @Singleton
    fun provideRatesListAdapter(
        listener: RateItemClickListener
    ): RatesListAdapter = RatesListAdapter(listener)

}
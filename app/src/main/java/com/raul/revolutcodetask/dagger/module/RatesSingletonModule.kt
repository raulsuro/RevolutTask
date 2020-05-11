package com.raul.revolutcodetask.dagger.module

import com.raul.revolutcodetask.data.repository.GetCountryRepositoryImpl
import com.raul.revolutcodetask.data.repository.GetRatesRepositoryImpl
import com.raul.revolutcodetask.domain.model.CountryInfo
import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.domain.model.state.StateManager
import com.raul.revolutcodetask.domain.repository.GetCountryRepository
import com.raul.revolutcodetask.domain.repository.GetRatesRepository
import dagger.Module
import dagger.Provides
import javax.inject.Inject
import javax.inject.Singleton

@Module
class RatesSingletonModule {

    lateinit var stateManagerCurrencies: StateManager<Currencies>
    lateinit var stateManagerCountry: StateManager<CountryInfo>

    @Inject
    fun provideRateManager(): StateManager<Currencies> =
        stateManagerCurrencies

    @Provides
    @Singleton
    fun provideGetRatesRepository(
        repo: GetRatesRepositoryImpl
    ): GetRatesRepository = repo


    @Inject
    fun provideCountryManager(): StateManager<CountryInfo> =
        stateManagerCountry

    @Provides
    @Singleton
    fun provideGetCountryRepository(
        repo: GetCountryRepositoryImpl
    ): GetCountryRepository = repo

}
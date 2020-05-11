package com.raul.revolutcodetask.dagger.module

import com.raul.revolutcodetask.data.mapper.NetworkToDomainCountriesMapper
import com.raul.revolutcodetask.data.mapper.NetworkToDomainCurrenciesMapper
import com.raul.revolutcodetask.presentation.mapper.DomainToPresentationCountryCurrenciesMapper
import dagger.Module
import javax.inject.Inject

@Module
class MappersModule {

    lateinit var currenciesMapper: NetworkToDomainCurrenciesMapper
    lateinit var countriesMapper: NetworkToDomainCountriesMapper
    lateinit var countryCurrenciesMapper: DomainToPresentationCountryCurrenciesMapper

    @Inject
    fun provideNetworkToDomainProductMapper(): NetworkToDomainCurrenciesMapper =
        currenciesMapper


    @Inject
    fun provideNetworkToDomainCountriesMapper(): NetworkToDomainCountriesMapper =
        countriesMapper

    @Inject
    fun provideDomainToPresentationCountryCurrenciesMapper(): DomainToPresentationCountryCurrenciesMapper =
        countryCurrenciesMapper


}
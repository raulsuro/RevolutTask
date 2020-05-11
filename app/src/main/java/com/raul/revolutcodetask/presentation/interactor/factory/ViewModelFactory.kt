package com.raul.revolutcodetask.presentation.interactor.factory

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.raul.revolutcodetask.domain.model.state.StateManager
import com.raul.revolutcodetask.domain.usecase.GetCountryUseCase
import com.raul.revolutcodetask.domain.usecase.GetRatesUseCase
import com.raul.revolutcodetask.presentation.interactor.MainViewModel
import com.raul.revolutcodetask.presentation.mapper.DomainToPresentationCountryCurrenciesMapper
import com.raul.revolutcodetask.presentation.model.CountryCurrencies
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private var resultManager: StateManager<CountryCurrencies>,
    private val mapper: DomainToPresentationCountryCurrenciesMapper,
    private val useCaseRates: GetRatesUseCase,
    private val useCaseCountry: GetCountryUseCase
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(
            resultManager,
            mapper,
            useCaseRates,
            useCaseCountry
        ) as T
    }
}
package com.raul.revolutcodetask.presentation.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raul.revolutcodetask.domain.model.CountryInfo
import com.raul.revolutcodetask.domain.model.ErrorEntity
import com.raul.revolutcodetask.domain.model.Output
import com.raul.revolutcodetask.domain.model.state.ScreenState
import com.raul.revolutcodetask.domain.model.state.StateManager
import com.raul.revolutcodetask.domain.usecase.GetCountryUseCase
import com.raul.revolutcodetask.domain.usecase.GetRatesUseCase
import com.raul.revolutcodetask.presentation.mapper.DomainToPresentationCountryCurrenciesMapper
import com.raul.revolutcodetask.presentation.model.CountryCurrencies
import kotlinx.coroutines.*
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private var resultManager: StateManager<CountryCurrencies>,
    private val mapper: DomainToPresentationCountryCurrenciesMapper,
    private val useCaseRates: GetRatesUseCase,
    private val useCaseCountry: GetCountryUseCase
) : ViewModel() {

    private val viewModelJob = SupervisorJob()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    private var jobRunning: Job? = null
    private val countryList = ArrayList<CountryInfo>()

    var results: LiveData<ScreenState<CountryCurrencies>> = resultManager

    fun initialize(code: String) {
        jobRunning?.let {
            if (it.isActive) it.cancel()
        } ?: resultManager.loading()

        jobRunning = uiScope.launch {
            var retries = 0
            while (retries < 4) {
                when (val rates = useCaseRates.execute(code)) {
                    is Output.Success -> {
                        retries = 0
                        if (countryList.isEmpty()) {
                            rates.data.rate.map {
                                when (val countries = useCaseCountry.execute(it.currency)) {
                                    is Output.Success -> {
                                        countryList.add(countries.data)
                                        retries = 0
                                    }
                                    is Output.Error -> {
                                        updateUi(countries.error)
                                        retries++
                                    }
                                }
                            }
                        }
                        updateUi(mapper.map(rates.data, countryList))
                    }
                    is Output.Error -> {
                        updateUi(rates.error)
                        retries++
                    }
                }
                delay(1000)
            }

        }
    }

    private fun updateUi(response: Any) {
        if (response is ErrorEntity)
            resultManager.error(response)
        else {
            resultManager.set(response as CountryCurrencies)
        }
    }
}
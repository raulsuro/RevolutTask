package com.raul.revolutcodetask.presentation.interactor

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.raul.revolutcodetask.domain.model.CountryInfo
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
        resultManager.loading()
        jobRunning?.let {
            if (it.isActive) it.cancel()
        }
        jobRunning = uiScope.launch {
            try {
                while (true) {
                    val rates = useCaseRates.execute(code)
                    if (countryList.isEmpty()) {
                        rates.rate.forEach {
                            countryList.add(useCaseCountry.execute(it.currency))
                        }
                    }
                    updateUi(mapper.map(rates, countryList))
                    delay(1000)
                }
            } catch (e: CancellationException) {
            } catch (e: Exception) {
                updateUi(e)
            }
        }
    }

    private fun updateUi(response: Any) {
        if (response is Throwable)
            resultManager.error(response)
        else {
            resultManager.set(response as CountryCurrencies)
        }
    }
}
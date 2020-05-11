package com.raul.revolutcodetask.presentation.interactor

import com.nhaarman.mockitokotlin2.verify
import com.raul.revolutcodetask.domain.model.state.StateManager
import com.raul.revolutcodetask.domain.usecase.GetCountryUseCase
import com.raul.revolutcodetask.domain.usecase.GetRatesUseCase
import com.raul.revolutcodetask.presentation.mapper.DomainToPresentationCountryCurrenciesMapper
import com.raul.revolutcodetask.presentation.model.CountryCurrencies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class MainViewModelTest {

    private val code = "EUR"
    private val countryCurrency = "CAD"

    @Mock
    lateinit var useCaseRates: GetRatesUseCase

    @Mock
    lateinit var useCaseCountry: GetCountryUseCase

    @Mock
    lateinit var resultManager: StateManager<CountryCurrencies>

    @Mock
    lateinit var mapper: DomainToPresentationCountryCurrenciesMapper

    lateinit var sut: MainViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        Dispatchers.setMain(mainThreadSurrogate)
        sut =
            MainViewModel(
                resultManager,
                mapper,
                useCaseRates,
                useCaseCountry
            )
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun `initialize should invoke GetRatesUsecase execute`() {
        runBlocking {
            sut.initialize(code)
            delay(2000)
            verify(useCaseRates).execute(code)
        }
    }

}
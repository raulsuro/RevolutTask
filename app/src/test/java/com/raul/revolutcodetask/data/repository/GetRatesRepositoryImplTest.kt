package com.raul.revolutcodetask.data.repository

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.raul.revolutcodetask.data.mapper.NetworkToDomainCurrenciesMapper
import com.raul.revolutcodetask.data.network.api.RatesApi
import com.raul.revolutcodetask.data.network.model.RatesApiResponse
import com.raul.revolutcodetask.domain.handler.ErrorHandler
import com.raul.revolutcodetask.domain.model.Currencies
import com.raul.revolutcodetask.domain.model.ErrorEntity
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response
import java.io.IOException

class GetRatesRepositoryImplTest {

    @Mock
    lateinit var errorHandler: ErrorHandler

    @Mock
    lateinit var ratesApi: RatesApi

    @Mock
    lateinit var mapper: NetworkToDomainCurrenciesMapper

    @Mock
    lateinit var responseRatesApiResponse: Response<RatesApiResponse>

    @Mock
    lateinit var ratesApiResponse: RatesApiResponse

    @Mock
    lateinit var currencies: Currencies

    lateinit var error: Throwable


    lateinit var sut: GetRatesRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetRatesRepositoryImpl(ratesApi, mapper, errorHandler)
        error = Throwable(IOException())
    }

    private val code = "EUR"

    @Test
    fun `retrieveRates should invoke RatesApi getRates`() {
        runBlocking {
            whenever(ratesApi.getRates(code)).thenReturn(responseRatesApiResponse)
            whenever(ratesApi.getRates(code).body()).thenReturn(ratesApiResponse)
            whenever(errorHandler.getError(error)).thenReturn(ErrorEntity.Unknown)

            whenever(mapper.map(ratesApiResponse)).thenReturn(currencies)


            val result = sut.retrieveRates(code)

            assertThat(result).isEqualTo(currencies)
            verify(ratesApi, times(2)).getRates(code)

        }
    }
}
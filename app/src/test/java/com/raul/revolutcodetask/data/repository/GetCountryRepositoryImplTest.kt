package com.raul.revolutcodetask.data.repository

import com.google.common.truth.Truth.assertThat
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.raul.revolutcodetask.data.mapper.NetworkToDomainCountriesMapper
import com.raul.revolutcodetask.data.network.api.CountriesApi
import com.raul.revolutcodetask.data.network.model.CountriesApiResponse
import com.raul.revolutcodetask.domain.model.CountryInfo
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import retrofit2.Response

class GetCountryRepositoryImplTest {

    @Mock
    lateinit var mapper: NetworkToDomainCountriesMapper

    @Mock
    lateinit var countriesApi: CountriesApi

    @Mock
    lateinit var reponseCountriesApiResponse: Response<List<CountriesApiResponse>>

    @Mock
    lateinit var countriesApiResponse: List<CountriesApiResponse>

    @Mock
    lateinit var countryInfo: CountryInfo

    lateinit var sut: GetCountryRepositoryImpl

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetCountryRepositoryImpl(countriesApi, mapper)
    }

    @Test
    fun `retrieveCountry should invoke CountryApi retrieveCountry`() {
        runBlocking {
            whenever(countriesApi.getCountry("EUR")).thenReturn(reponseCountriesApiResponse)
            whenever(countriesApi.getCountry("EUR").body()).thenReturn(countriesApiResponse)

            whenever(mapper.map(countriesApiResponse)).thenReturn(countryInfo)

            val result = sut.retrieveCountry("EUR")

            assertThat(result).isEqualTo(countryInfo)
            verify(countriesApi, times(2)).getCountry("EUR")
        }
    }
}
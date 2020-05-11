package com.raul.revolutcodetask.domain.usecase

import com.nhaarman.mockitokotlin2.verify
import com.raul.revolutcodetask.domain.repository.GetRatesRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetRatesUseCaseTest {

    @Mock
    lateinit var repository: GetRatesRepository

    lateinit var sut: GetRatesUseCase

    private val code = "EUR"

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetRatesUseCase(repository)
    }

    @Test
    fun `execute should invoke GetRatesRepository retrieveRates`() {
        runBlocking {
            sut.execute(code)
            verify(repository).retrieveRates(code)
        }
    }
}
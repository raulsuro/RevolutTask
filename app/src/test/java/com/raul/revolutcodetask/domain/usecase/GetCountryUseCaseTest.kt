package com.raul.revolutcodetask.domain.usecase

import com.nhaarman.mockitokotlin2.verify
import com.raul.revolutcodetask.domain.repository.GetCountryRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

class GetCountryUseCaseTest {

    @Mock
    lateinit var repository: GetCountryRepository

    lateinit var sut: GetCountryUseCase

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        sut = GetCountryUseCase(repository)
    }

    @Test
    fun `execute should invoke GetCountryRepository retrieveCountry`() {
        runBlocking {
            sut.execute("EUR")
            verify(repository).retrieveCountry("EUR")
        }

    }
}
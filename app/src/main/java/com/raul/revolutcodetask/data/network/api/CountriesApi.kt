package com.raul.revolutcodetask.data.network.api

import com.raul.revolutcodetask.data.network.model.CountriesApiResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface CountriesApi {
    @GET("{code}")
    suspend fun getCountry(
        @Path("code") code: String
    ): Response<List<CountriesApiResponse>>
}
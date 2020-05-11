package com.raul.revolutcodetask.dagger.module

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.raul.revolutcodetask.RatesApp
import com.raul.revolutcodetask.data.network.api.CountriesApi
import com.raul.revolutcodetask.data.network.api.RatesApi
import com.raul.revolutcodetask.data.network.model.RatesApiResponse
import com.raul.revolutcodetask.data.serialization.RatesDeserializer
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class NetworkModule {

    private val BASE_URL = "https://hiring.revolut.codes/api/android/"
    private val BASE_URL2 = "https://restcountries.eu/rest/v2/currency/"

    @Provides
    @Singleton
    fun createCountriesApi(application: RatesApp): CountriesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL2)
            .addConverterFactory(GsonConverterFactory.create(Gson()))
            .client(createOkHttpClient(application))
            .build()
            .create(CountriesApi::class.java)
    }

    @Provides
    @Singleton
    fun createRatesApi(application: RatesApp): RatesApi {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(createGson()))

            .client(createOkHttpClient(application))
            .build()
            .create(RatesApi::class.java)
    }

    @Provides
    @Singleton
    fun createGson(): Gson {
        return GsonBuilder().registerTypeAdapter(RatesApiResponse::class.java, RatesDeserializer())
            .create()
    }

    @Provides
    @Singleton
    fun createOkHttpClient(application: RatesApp): OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY

        val clientBuilder = OkHttpClient.Builder()
        clientBuilder.addInterceptor(loggingInterceptor)
        clientBuilder.addInterceptor(ChuckInterceptor(application.applicationContext))
        return clientBuilder.build()
    }
}
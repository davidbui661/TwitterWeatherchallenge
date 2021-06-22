package com.example.twitterweatherchallenge.data.remote

import com.example.twitterweatherchallenge.BuildConfig
import com.example.twitterweatherchallenge.constants.TIMEOUT
import com.example.twitterweatherchallenge.constants.BASE_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitInstance {

    private fun providesOkHttpClient(): OkHttpClient {
        val httpLoggingInterceptor =
            HttpLoggingInterceptor(HttpLoggingInterceptor.Logger.DEFAULT)
        val clientBuilder =
            OkHttpClient.Builder().connectTimeout(TIMEOUT, TimeUnit.SECONDS).readTimeout(
                TIMEOUT,
                TimeUnit.SECONDS
            ).writeTimeout(TIMEOUT, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(httpLoggingInterceptor)
        }

        return clientBuilder.build()
    }

    fun providesRetrofitService(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(providesOkHttpClient())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

}
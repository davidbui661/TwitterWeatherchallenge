package com.example.twitterweatherchallenge.data.remote

import com.example.twitterweatherchallenge.data.models.WeatherResponseDTO
import retrofit2.Response

class WeatherManager {

    private val service: WeatherService
    private val retrofit = RetrofitInstance.providesRetrofitService()

    init {
        service = retrofit.create(WeatherService::class.java)
    }

    suspend fun getCurrentWeather(): Response<WeatherResponseDTO> {
        return service.getCurrentWeather()
    }

    suspend fun getFutureWeather(days: String):Response<WeatherResponseDTO> {
        return service.getFutureWeather(days)
    }

}
package com.example.twitterweatherchallenge.data.repository

import com.example.twitterweatherchallenge.data.models.WeatherResponseDTO
import com.example.twitterweatherchallenge.data.remote.WeatherService
import retrofit2.Response
import javax.inject.Inject

class WeatherRepository @Inject constructor(private val weatherService: WeatherService) {

    suspend fun getCurrentWeather(): Response<WeatherResponseDTO> {
        return weatherService.getCurrentWeather()
    }

    suspend fun getFutureWeather(day: String): Response<WeatherResponseDTO> {
        return weatherService.getFutureWeather(day)
    }
}
package com.example.twitterweatherchallenge.data.remote

import com.example.twitterweatherchallenge.data.models.WeatherResponseDTO
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface WeatherService {

    @GET("current.json")
    suspend fun getCurrentWeather(): Response<WeatherResponseDTO>

    @GET("future_{day}.json")
    suspend fun getFutureWeather(
        @Path("day") day: String
    ): Response<WeatherResponseDTO>
}
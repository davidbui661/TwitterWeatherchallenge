package com.example.twitterweatherchallenge.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RainDTO(
    @field:Json(name = "3h")val threeH: Double?
)

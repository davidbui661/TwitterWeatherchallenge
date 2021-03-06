package com.example.twitterweatherchallenge.data.models

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CloudsDTO(
    @field:Json(name = "cloudiness")val cloudiness: Double?
)

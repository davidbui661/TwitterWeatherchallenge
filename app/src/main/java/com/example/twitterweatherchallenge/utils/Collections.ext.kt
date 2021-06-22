package com.example.twitterweatherchallenge.utils

import kotlin.math.pow
import kotlin.math.sqrt

fun List<Double>.calculateStandardDeviation(): Double {
    var sum = 0.0
    var standardDeviation = 0.0

    for (num in this) {
        sum += num
    }

    val mean = sum / 10

    for (num in this) {
        standardDeviation += (num - mean).pow(2.0)
    }

    return sqrt(standardDeviation / 10)
}
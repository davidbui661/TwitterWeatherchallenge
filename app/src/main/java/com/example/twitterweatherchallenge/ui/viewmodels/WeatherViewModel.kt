package com.example.twitterweatherchallenge.ui.viewmodels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.twitterweatherchallenge.constants.NUMBER_OF_DAYS
import com.example.twitterweatherchallenge.data.models.WeatherResponseDTO
import com.example.twitterweatherchallenge.data.repository.WeatherRepository
import com.example.twitterweatherchallenge.utils.calculateStandardDeviation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val weatherRepo: WeatherRepository
    ) : ViewModel() {

    private var _currentWeather: MutableLiveData<WeatherResponseDTO> = MutableLiveData()
    val currentWeather: LiveData<WeatherResponseDTO> get() = _currentWeather

    private var _standardDeviation: MutableLiveData<Double> = MutableLiveData()
    val standardDeviation: LiveData<Double> get() = _standardDeviation

    fun getCurrentWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val weather = weatherRepo.getCurrentWeather()
                if (weather.isSuccessful && weather.body() != null) {
                    _currentWeather.postValue(weather.body())
                }
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    fun getFutureWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = (1..NUMBER_OF_DAYS).map { day ->
                    weatherRepo.getFutureWeather(day.toString())
                }.filter {
                    it.isSuccessful && it.body() != null
                }

                val flattenedWeatherList = response
                    .flatMap { weather ->
                        mutableListOf(weather.body()?.weather?.temp!!).also {
                            it.add(weather.body()?.weather?.temp!!)
                        }
                    }

                _standardDeviation.postValue(flattenedWeatherList.calculateStandardDeviation())
            } catch (e: Exception) {
                Log.d(TAG, e.toString())
            }
        }
    }

    companion object {
        private val TAG = WeatherViewModel::class.java.name
    }
}

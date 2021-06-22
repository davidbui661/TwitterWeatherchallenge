package com.example.twitterweatherchallenge.ui.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.twitterweatherchallenge.R
import com.example.twitterweatherchallenge.constants.MIN_CLOUDS
import com.example.twitterweatherchallenge.databinding.WeatherActivityBinding
import com.example.twitterweatherchallenge.ui.viewmodels.WeatherViewModel
import com.example.twitterweatherchallenge.utils.convertToFahrenheit
import com.example.twitterweatherchallenge.utils.getStringValue
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModels()
    private lateinit var binding: WeatherActivityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = WeatherActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState?.get("celcius") == null) {
            weatherViewModel.getCurrentWeather()
        }

        with(binding) {
            weatherViewModel.currentWeather.observe(this@WeatherActivity) {
                celsiusTv.text = it.weather?.temp.toString()
                fahrenheitTv.text = it.weather?.temp?.convertToFahrenheit().toString()
                windSpeedTv.text = it.wind?.speed.toString()
                if (it.clouds?.cloudiness?.compareTo(MIN_CLOUDS)!! > 0) {
                    cloudIconIv.setImageResource(R.drawable.ic_baseline_cloud_24)
                }
            }

            weatherButton.setOnClickListener {
                weatherViewModel.getFutureWeather()
            }

            weatherViewModel.standardDeviation.observe(this@WeatherActivity) {
                deviationTv.text = it.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        with(binding) {
            outState.putString("celsius", celsiusTv.getStringValue())
            outState.putString("fahrenheit", fahrenheitTv.getStringValue())
            outState.putString("windSpeed", windSpeedTv.getStringValue())
            outState.putString("deviation", deviationTv.getStringValue())
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        with(binding) {
            celsiusTv.text = savedInstanceState.getString("celsius")
            fahrenheitTv.text = savedInstanceState.getString("fahrenheit")
            windSpeedTv.text = savedInstanceState.getString("wind").toString()
            deviationTv.text = savedInstanceState.getString("deviation")
        }

    }


}
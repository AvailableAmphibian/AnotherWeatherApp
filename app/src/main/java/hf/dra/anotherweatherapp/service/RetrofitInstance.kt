package hf.dra.anotherweatherapp.service

import hf.dra.anotherweatherapp.OPEN_WEATHER_URL
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    val OPEN_WEATHER: ServiceWeather by lazy {
        Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(OPEN_WEATHER_URL)
            .build()
            .create(ServiceWeather::class.java)
    }
}
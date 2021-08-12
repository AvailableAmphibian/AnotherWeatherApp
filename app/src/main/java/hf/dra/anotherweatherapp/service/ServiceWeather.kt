package hf.dra.anotherweatherapp.service

import hf.dra.anotherweatherapp.API_KEY
import hf.dra.anotherweatherapp.WEATHER
import hf.dra.anotherweatherapp.model.CityData
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ServiceWeather {
    @GET(WEATHER)
    fun getByCityName(
        @Query("q") cityName: String,
        @Query("appid") appId: String = API_KEY,
        @Query("units") units: String = "standard"
    ): Call<CityData>

    @GET(WEATHER)
    fun getByCityId(
        @Query("id") cityId: Int,
        @Query("appid") appId: String = API_KEY,
        @Query("units") units: String = "standard"
    ): Call<CityData>

    @GET(WEATHER)
    fun getByLatLon(
        @Query("lat") lat: Float,
        @Query("lon") lon: Float,
        @Query("appid") appId: String = API_KEY,
        @Query("units") units: String = "standard"
    ): Call<CityData>
}
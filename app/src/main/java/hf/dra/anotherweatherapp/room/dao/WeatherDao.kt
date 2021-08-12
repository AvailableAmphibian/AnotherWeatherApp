package hf.dra.anotherweatherapp.room.dao

import androidx.room.Dao
import hf.dra.anotherweatherapp.model.Weather
@Dao
interface WeatherDao:BaseDao<Weather> {
}
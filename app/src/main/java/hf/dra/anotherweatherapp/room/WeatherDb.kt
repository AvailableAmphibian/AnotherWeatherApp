package hf.dra.anotherweatherapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hf.dra.anotherweatherapp.model.*
import hf.dra.anotherweatherapp.room.dao.CityDao
import hf.dra.anotherweatherapp.room.dao.MainDataDao
import hf.dra.anotherweatherapp.room.dao.WeatherDao
import hf.dra.anotherweatherapp.room.dao.WindDao
import hf.dra.anotherweatherapp.room.entities.CityDataRelationships

@Database(entities = [CityData::class,MainData::class, Sys::class, Weather::class, Wind::class], version = 1)
abstract class WeatherDb : RoomDatabase() {
    companion object {
        private lateinit var instance: WeatherDb

        fun getInstance() = instance

        fun initInstance(context: Context): WeatherDb {
            instance = Room.databaseBuilder(
                context.applicationContext,
                WeatherDb::class.java,
                "weather-database"
            ).build()
            return instance
        }
    }

    abstract fun cityDao():CityDao

    abstract fun mainDataDao():MainDataDao
    abstract fun weatherDao():WeatherDao
    abstract fun windDao():WindDao
}
package hf.dra.anotherweatherapp.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import hf.dra.anotherweatherapp.model.*

@Database(entities = [CityData::class], version = 1, exportSchema = false)
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

    abstract fun cityDao(): CityDao
}
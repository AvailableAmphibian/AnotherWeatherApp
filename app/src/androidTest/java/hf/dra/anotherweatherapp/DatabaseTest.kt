package hf.dra.anotherweatherapp

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import hf.dra.anotherweatherapp.model.*
import hf.dra.anotherweatherapp.room.CityDao
import hf.dra.anotherweatherapp.room.WeatherDb
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.CoreMatchers.nullValue
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.AfterClass
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.io.IOException

@RunWith(AndroidJUnit4::class)
class DatabaseTest {
    private lateinit var cityDao: CityDao
    private lateinit var db: WeatherDb
    private lateinit var cityData: CityData

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = WeatherDb.initInstance(context)
        cityDao = db.cityDao()
        cityData = CityData(
            1,
            "Mock",
            -25200,
            Weather("drizzle", "heavy thunderstorm", "10d"),
            MainData(20.0F, 22.0F, 44),
            Wind(12.3F, 96, 0F),
            Sys("JP", 111111111L, 22222222L)
        )
    }

    @Test
    @Throws(Exception::class)
    fun writeCityDataAndReadInList() {
        runBlocking {
            cityDao.insert(cityData)
            val retrieved = cityDao.getCityById(1)
            assertThat(retrieved, equalTo(cityData))
        }
    }

    @Test
    @Throws(Exception::class)
    fun insertMultiple() {
        runBlocking {
            cityDao.insert(cityData)

            cityDao.insert(cityData)
            assert(true)
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteData() {
        runBlocking {
            cityDao.insert(cityData)

            cityDao.delete(cityData)
            val retrieved = cityDao.getCityById(1)
            assertThat(retrieved, nullValue())
        }
    }

    @Test
    @Throws(Exception::class)
    fun deleteById() {
        runBlocking {
            cityDao.insert(cityData)

            cityDao.deleteById(1)
            val retrieved = cityDao.getCityById(1)
            assertThat(retrieved, nullValue())
        }
    }

    @After
    @Throws(IOException::class)
    fun closeDb() {
        db.clearAllTables()
        db.close()
    }
}
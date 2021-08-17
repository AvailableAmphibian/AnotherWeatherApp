package hf.dra.anotherweatherapp.room

import androidx.room.*
import hf.dra.anotherweatherapp.model.CityData

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(cityData: CityData)

    @Delete
    fun delete(cityData: CityData)

    @Transaction
    fun deleteById(id: Int){
        val city = getCityById(id)?:return
        delete(city)
    }

    @Query("Select * from cityData where id = :id limit 1")
    fun getCityById(id: Int): CityData?
}
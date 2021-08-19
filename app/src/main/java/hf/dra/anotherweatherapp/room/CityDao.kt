package hf.dra.anotherweatherapp.room

import androidx.room.*
import hf.dra.anotherweatherapp.model.CityData

@Dao
interface CityDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(cityData: CityData)

    @Delete
    suspend fun delete(cityData: CityData)

    @Transaction
    suspend fun deleteById(id: Int) {
        val city = getCityById(id) ?: return
        delete(city)
    }

    @Query("Select * from cityData where id = :id limit 1")
    suspend fun getCityById(id: Int): CityData?

    @Query("Select * from cityData where favorite = 1")
    suspend fun getAllFavoriteCities(): List<CityData>
}
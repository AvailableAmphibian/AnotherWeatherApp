package hf.dra.anotherweatherapp.room.dao

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Transaction
import hf.dra.anotherweatherapp.model.CityData
import hf.dra.anotherweatherapp.room.entities.CityDataRelationships

@Dao
interface CityDao:BaseDao<CityData> {
    @Transaction
    @Query("Select * from cityData where id = :id limit 1")
    fun getCityById(id:Int):CityDataRelationships?
}
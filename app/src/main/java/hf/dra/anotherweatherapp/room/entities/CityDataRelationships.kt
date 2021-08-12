package hf.dra.anotherweatherapp.room.entities

import androidx.room.Embedded
import androidx.room.Relation
import hf.dra.anotherweatherapp.model.*

data class CityDataRelationships(
    @Embedded val cityData: CityData,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId"
    )
    val weather: List<Weather>,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId"
    )
    val wind: Wind,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId"
    )
    val sys: Sys,
    @Relation(
        parentColumn = "id",
        entityColumn = "cityId"
    )
    val main: MainData
) {
    constructor(cityData: CityData) : this(cityData, cityData.weather!!, cityData.wind!!, cityData.sys!!, cityData.main!!)

    fun toCityData(): CityData {
        return cityData.apply {
            weather = this@CityDataRelationships.weather
            wind = this@CityDataRelationships.wind
            sys = this@CityDataRelationships.sys
            main = this@CityDataRelationships.main
        }
    }
}
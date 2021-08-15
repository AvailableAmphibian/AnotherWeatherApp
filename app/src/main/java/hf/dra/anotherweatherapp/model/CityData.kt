package hf.dra.anotherweatherapp.model

import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import hf.dra.anotherweatherapp.countryAsEmoji
import hf.dra.anotherweatherapp.room.entities.CityDataRelationships

@Entity
data class CityData(
    @PrimaryKey var id: Int,//PK
    var name: String,

    @Ignore var coord: Coord? = null,//Ignored
    @Ignore var weather: List<Weather>? = null,
    @Ignore var main: MainData? = null,
    @Ignore var wind: Wind? = null,
    @Ignore var rain: Precipitation? = null,//Ignored
    @Ignore var snow: Precipitation? = null,//Ignored
    @Ignore var sys: Sys? = null
) {
    constructor(id: Int, name: String) : this(
        id = id,
        name = name,
        null, null, null, null, null, null, null
    )

    fun toEntity(): CityDataRelationships {
        return CityDataRelationships(this)
    }

    val firstWeather: Weather
        get() = weather?.get(0)!!

    val country: String
        get() = countryAsEmoji(sys?.country ?: "")

    val temp get() = main!!.temp
    val feelsLike get() = main!!.feels_like
    val minTemp get() = main!!.temp_min
    val maxTemp get() = main!!.temp_max


}

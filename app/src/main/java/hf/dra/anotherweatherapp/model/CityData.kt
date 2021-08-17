package hf.dra.anotherweatherapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import hf.dra.anotherweatherapp.countryAsEmoji

//TODO Add favorite handling
@Entity
data class CityData(
    @PrimaryKey var id: Int,//PK
    var name: String,

    @Embedded @Transient var weather: Weather,

    @Embedded var main: MainData,
    @Embedded var wind: Wind,
    @Embedded var sys: Sys,

    @Ignore var coord: Coord? = null,//Ignored
    @Ignore var rain: Precipitation? = null,//Ignored
    @Ignore var snow: Precipitation? = null//Ignored
) {
    constructor(
        id: Int,
        name: String,
        weather: Weather,
        main: MainData,
        wind: Wind,
        sys: Sys
    ) : this(id, name, weather, main, wind, sys, null)

    @Ignore
    @SerializedName("weather")
    var weatherList: List<Weather>? = null


    val firstWeather: Weather
        get() = weatherList?.get(0) ?: weather

    val country: String
        get() = countryAsEmoji(sys.country ?: "")

    val temp get() = main.temp
    val feelsLike get() = main.feels_like

    override fun equals(other: Any?): Boolean {
        if (this === other)
            return true

        val data = other as CityData? ?: return false

        return data.id == id
                && data.name == name
                && main == data.main
                && weather == data.weather
                && wind == data.wind
                && sys == data.sys
    }

    override fun hashCode(): Int {
        var result = id
        result = 31 * result + name.hashCode()
        result = 31 * result + (weather.hashCode())
        result = 31 * result + (main.hashCode())
        result = 31 * result + (wind.hashCode())
        result = 31 * result + (sys.hashCode())
        return result
    }
}

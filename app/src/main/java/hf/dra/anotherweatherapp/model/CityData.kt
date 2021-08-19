package hf.dra.anotherweatherapp.model

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import hf.dra.anotherweatherapp.countryAsEmoji
import java.time.LocalDateTime
import java.time.OffsetDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import kotlin.math.roundToInt

@Entity(
    ignoredColumns = ["coord", "rain", "snow"]
)
data class CityData(
    @PrimaryKey var id: Int,//PK
    var name: String,
    var timezone: Int,
    @Embedded @Transient var weather: Weather,

    @Embedded var main: MainData,
    @Embedded var wind: Wind,
    @Embedded var sys: Sys,

    var favorite: Boolean = false,

    var lastFetched: Long = 0,
    var coord: Coord? = null,//Ignored
    var rain: Precipitation? = null,//Ignored
    var snow: Precipitation? = null//Ignored
) {
    //DB constructor
    constructor(
        id: Int,
        name: String,
        timezone: Int,
        weather: Weather,
        main: MainData,
        wind: Wind,
        sys: Sys
    ) : this(id, name, timezone, weather, main, wind, sys, favorite = true)


    @Ignore
    @SerializedName("weather")
    var weatherList: List<Weather>? = null

    //---- Data Helpers ----
    val firstWeather: Weather
        get() = weatherList?.get(0) ?: weather

    val country: String
        get() = countryAsEmoji(sys.country ?: "")

    val temp get() = main.temp
    val feelsLike get() = main.feels_like
    val humidity get() = "Humidity : ${main.humidity}%"

    val precipitation get() = rain ?: snow
    val precipitationName get() = if (rain != null) "Rain" else if (snow != null) "Snow" else ""
    val lastHour get() = if (precipitation != null) "Last hour : ${precipitation!!.lastHour} mm" else ""
    val lastThreeHours get() = if (precipitation != null) "Last 3 hours : ${precipitation!!.lastThreeHours} mm" else ""

    val windSpeed get() = wind.speed
    val windDegree get() = "Direction : ${wind.deg}°"
    val windGust get() = wind.gust

    val sunrise get() = convertLongToTime(sys.sunrise, "HH:mm")
    val sunset get() = convertLongToTime(sys.sunset, "HH:mm")

    val progress: Int
        get() {
            val max = sys.sunset - sys.sunrise
            val current = (System.currentTimeMillis() / 1000 - sys.sunrise) * 100
            return (current.toFloat() / max.toFloat()).roundToInt()
        }

    val readableLastFetched: String
        get() = "Last fetched : ${
            convertLongToTime(
                sys.sunset,
                " yyyy-MM-dd @ HH:mm"
            )
        }"

    private fun convertLongToTime(time: Long, pattern: String): String {
        val converted = OffsetDateTime.of(
            LocalDateTime.ofEpochSecond(
                time,
                0,
                ZoneOffset.ofTotalSeconds(timezone)
            ), ZoneOffset.ofTotalSeconds(timezone)
        )
        return converted.format(DateTimeFormatter.ofPattern(pattern))
    }
    //---- End ofData Helpers ----


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

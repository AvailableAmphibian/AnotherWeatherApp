package hf.dra.anotherweatherapp.model

import com.google.gson.annotations.SerializedName

data class Precipitation(
    @SerializedName("1h") val lastHour: Float,
    @SerializedName("3h") val lastThreeHours: Float
)

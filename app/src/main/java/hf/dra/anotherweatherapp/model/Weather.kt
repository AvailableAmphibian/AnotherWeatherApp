package hf.dra.anotherweatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Weather(
    @PrimaryKey(autoGenerate = true) var pk:Int,

    var cityId:Long,
    val main: String,
    val description: String,
    val icon: String
)

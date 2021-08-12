package hf.dra.anotherweatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Sys(
    @PrimaryKey(autoGenerate = true) var pk:Int,

    var cityId:Long,
    val country: String,
    val sunrise: Long,
    val sunset: Long
)

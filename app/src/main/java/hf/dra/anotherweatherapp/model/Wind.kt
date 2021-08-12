package hf.dra.anotherweatherapp.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Wind(
    @PrimaryKey(autoGenerate = true) var pk:Int,

    var cityId:Long,
    val speed: Float,
    val deg: Int,
    val gust: Float
)

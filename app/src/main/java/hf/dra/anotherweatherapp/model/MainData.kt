package hf.dra.anotherweatherapp.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity
data class MainData(
    @PrimaryKey(autoGenerate = true) var pk:Int,

    var cityId:Long,
    var temp: Float,
    var feels_like: Float,
    var humidity: Int,
    var temp_min: Float,
    var temp_max: Float
)

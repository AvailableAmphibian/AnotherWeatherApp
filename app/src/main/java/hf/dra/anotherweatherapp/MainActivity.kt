package hf.dra.anotherweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import hf.dra.anotherweatherapp.model.Units
import hf.dra.anotherweatherapp.room.WeatherDb
import kotlin.math.roundToInt

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherDb.initInstance(this)
    }


}


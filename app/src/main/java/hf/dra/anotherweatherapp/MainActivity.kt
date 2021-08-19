package hf.dra.anotherweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import hf.dra.anotherweatherapp.room.WeatherDb

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherDb.initInstance(this)
    }
}


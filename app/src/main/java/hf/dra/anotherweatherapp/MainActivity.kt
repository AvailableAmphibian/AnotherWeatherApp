package hf.dra.anotherweatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.google.gson.Gson
import hf.dra.anotherweatherapp.fragments.SearchFragment
import hf.dra.anotherweatherapp.model.CityJson
import hf.dra.anotherweatherapp.room.WeatherDb

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        WeatherDb.initInstance(this)
    }
}


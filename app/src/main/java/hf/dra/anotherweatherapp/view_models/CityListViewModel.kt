package hf.dra.anotherweatherapp.view_models

import android.content.res.AssetManager
import android.util.Log
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import hf.dra.anotherweatherapp.MainActivity
import hf.dra.anotherweatherapp.model.CityJson

class CityListViewModel:ViewModel() {
    lateinit var cityList: List<CityJson>
        private set

    fun initCityListJson(assets: AssetManager){
        val gson = Gson()
        val reader = assets.open("city.list.json").bufferedReader()

        cityList = gson.fromJson(reader, Array<CityJson>::class.java).asList()

        reader.close()
    }
}

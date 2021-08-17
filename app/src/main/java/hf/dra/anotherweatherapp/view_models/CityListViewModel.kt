package hf.dra.anotherweatherapp.view_models

import android.content.res.AssetManager
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import hf.dra.anotherweatherapp.model.CityJson

class CityListViewModel : ViewModel() {
    lateinit var cityList: List<CityJson>
        private set

    fun initCityListJson(assets: AssetManager) {
        val gson = Gson()

        assets.open("city.list.json").bufferedReader()
            .use { cityList = gson.fromJson(it, Array<CityJson>::class.java).asList() }
    }
}

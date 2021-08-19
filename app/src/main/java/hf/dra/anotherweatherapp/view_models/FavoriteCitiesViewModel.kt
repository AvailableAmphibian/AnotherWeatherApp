package hf.dra.anotherweatherapp.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hf.dra.anotherweatherapp.model.CityData
import hf.dra.anotherweatherapp.room.WeatherDb
import kotlinx.coroutines.launch

class FavoriteCitiesViewModel: ViewModel() {
    var cities: MutableLiveData<List<CityData>> = MutableLiveData<List<CityData>>().also {
        viewModelScope.launch {
            it.value = WeatherDb.getInstance().cityDao().getAllFavoriteCities()
        }
    }
    private set
}
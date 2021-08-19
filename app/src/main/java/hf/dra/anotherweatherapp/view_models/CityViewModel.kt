package hf.dra.anotherweatherapp.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import hf.dra.anotherweatherapp.model.CityData
import hf.dra.anotherweatherapp.room.WeatherDb
import kotlinx.coroutines.launch

class CityViewModel : ViewModel() {
    val selected = MutableLiveData<CityData>()

    private val dao get() = WeatherDb.getInstance().cityDao()
    private val value get() = selected.value!!

    suspend fun verifyIfInDb() {
        val inDb = dao.getCityById(value.id)
        if (inDb != null) {
            value.favorite = true
            dao.insert(value)
        }

    }

    fun insertData() {
        viewModelScope.launch {
            WeatherDb.getInstance().cityDao().insert(value)
        }
    }

    fun deleteData() {
        viewModelScope.launch {
            WeatherDb.getInstance().cityDao().delete(value)
        }
    }
}

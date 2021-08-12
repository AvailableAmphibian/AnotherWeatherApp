package hf.dra.anotherweatherapp.view_models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import hf.dra.anotherweatherapp.model.CityData

class CityViewModel : ViewModel() {
    val selected = MutableLiveData<CityData>()
}

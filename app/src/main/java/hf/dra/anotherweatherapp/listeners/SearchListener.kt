package hf.dra.anotherweatherapp.listeners

import hf.dra.anotherweatherapp.model.CityJson

interface SearchListener {
    fun onClickSearch(item:CityJson)
}
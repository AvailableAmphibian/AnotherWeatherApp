package hf.dra.anotherweatherapp.listeners

import hf.dra.anotherweatherapp.model.CityJson


fun interface OnClickSearch {
    fun onClickSearch(item:CityJson)
}
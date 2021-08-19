package hf.dra.anotherweatherapp.listeners

import hf.dra.anotherweatherapp.model.CityData

fun interface OnClickFavorite {
    fun onClickFav(item:CityData)
}
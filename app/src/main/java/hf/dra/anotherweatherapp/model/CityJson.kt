package hf.dra.anotherweatherapp.model

import hf.dra.anotherweatherapp.countryAsEmoji


data class CityJson(
    val id: Int,
    val name: String,
    val state: String,
    val country: String,
) {
    val countryText: String
        get() = if (state == "") countryAsEmoji(country) else "${countryAsEmoji(country)}, $state"
}

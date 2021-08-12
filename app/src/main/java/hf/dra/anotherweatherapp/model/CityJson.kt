package hf.dra.anotherweatherapp.model

import android.util.Log
import hf.dra.anotherweatherapp.EMOJI_OFFSET


data class CityJson(
    val id: Int,
    val name: String,
    val state: String,
    val country: String,
) {
    fun getCountryText(): String =
        if (state == "") countryAsEmoji() else "${countryAsEmoji()}, $state"

    private fun countryAsEmoji(): String {
        val sb = StringBuilder()

        for (char in country)
            sb.appendCodePoint(char.code + EMOJI_OFFSET)

        Log.i("CityJson", "countryAsCode: $sb")

        return sb.toString()
    }


}

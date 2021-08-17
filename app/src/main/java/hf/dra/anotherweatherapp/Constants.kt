package hf.dra.anotherweatherapp

const val OPEN_WEATHER_URL = "https://api.openweathermap.org/data/2.5/"
const val API_KEY = "7c0e64bf3a94387b34f0ca323fc8431e"

const val WEATHER = "weather"

const val EMOJI_OFFSET = 127397

fun countryAsEmoji(country:String): String {
    val sb = StringBuilder()

    for (char in country)
        sb.appendCodePoint(char.code + EMOJI_OFFSET)

    return sb.toString()
}


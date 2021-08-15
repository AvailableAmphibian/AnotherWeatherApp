package hf.dra.anotherweatherapp.model

enum class Units(val degree: String, val speed: String) {
    STANDARD("°K", "m/s"),
    IMPERIAL("°F", "mi/h"),
    METRIC("°C", "m/s");

    companion object{
        fun getValueOf(value: String): Units {
            return when (value.lowercase()) {
                "standard" -> STANDARD
                "imperial" -> IMPERIAL
                "metric" -> METRIC
                else -> throw IllegalArgumentException()
            }

        }
    }
}
package hf.dra.anotherweatherapp.fragments

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.preference.PreferenceManager
import hf.dra.anotherweatherapp.MainActivity
import hf.dra.anotherweatherapp.R
import hf.dra.anotherweatherapp.databinding.FragmentCityBinding
import hf.dra.anotherweatherapp.model.Units
import hf.dra.anotherweatherapp.view_models.CityViewModel
import kotlin.math.roundToInt


class CityFragment : Fragment() {
    private var _binding: FragmentCityBinding? = null

    private val viewModel: CityViewModel by activityViewModels()

    //Private getters
    private val binding get() = _binding!!
    private val mainActivity get() = requireActivity() as MainActivity
    private val data get() = binding.data!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCityBinding.inflate(inflater, container, false)

        viewModel.selected.observe(viewLifecycleOwner, {
            binding.data = it
        })

        binding.fragment = this

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    //Fragment construction needing context
    val temp get() = getTemperature(data.temp)
    val feelsLike get() = getTemperature(data.feelsLike)
    val maxTemp get() = getTemperature(data.maxTemp)
    val minTemp get() = getTemperature(data.minTemp)

    private fun getTemperature(temp: Float): String {
        val pref = Units.getValueOf(PreferenceManager.getDefaultSharedPreferences(mainActivity).getString("Unit","Standard")!!)
        val degrees = temp.roundToInt()
        return "$degrees${pref.degree}"
    }

    val firstIcon: Drawable?
        get() = ContextCompat.getDrawable(
            requireContext(), when (data.firstWeather.icon) {
                //ClearSky
                "01d" -> R.drawable._01d
                "01n" -> R.drawable._01n

                //Few clouds
                "02d" -> R.drawable._02d
                "02n" -> R.drawable._02n

                //Scattered clouds
                "03d" -> R.drawable._03d
                "03n" -> R.drawable._03n

                //Broken clouds
                "04d" -> R.drawable._04d
                "04n" -> R.drawable._04n

                //Shower rain
                "09d" -> R.drawable._09d
                "09n" -> R.drawable._09n

                //Rain
                "10d" -> R.drawable._10d
                "10n" -> R.drawable._10n

                //Thunderstorm
                "11d" -> R.drawable._11d
                "11n" -> R.drawable._11n

                //Snow
                "13d" -> R.drawable._13d
                "13n" -> R.drawable._13n

                //Mist
                "50d" -> R.drawable._50d
                "50n" -> R.drawable._50n

                else -> 0
            }
        )
}
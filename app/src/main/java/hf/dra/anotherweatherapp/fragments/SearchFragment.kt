package hf.dra.anotherweatherapp.fragments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hf.dra.anotherweatherapp.R
import hf.dra.anotherweatherapp.adapters.CityJsonAdapter
import hf.dra.anotherweatherapp.databinding.FragmentSearchBinding
import hf.dra.anotherweatherapp.model.CityData
import hf.dra.anotherweatherapp.model.CityJson
import hf.dra.anotherweatherapp.room.WeatherDb
import hf.dra.anotherweatherapp.service.RetrofitInstance
import hf.dra.anotherweatherapp.view_models.CityListViewModel
import hf.dra.anotherweatherapp.view_models.CityViewModel
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.streams.toList

class SearchFragment : Fragment() /*,SearchListener*/ {
    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CityViewModel by activityViewModels()
    private val value get() = viewModel.selected.value!!
    private val listViewModel: CityListViewModel by activityViewModels()

    private lateinit var displayedCities: ArrayList<CityJson>
    private lateinit var adapter: CityJsonAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Binding handling
        _binding = FragmentSearchBinding.inflate(inflater, container, false)

        binding.fragment = this

        //Rv handling
        displayedCities = ArrayList(listViewModel.cityList)
        adapter = CityJsonAdapter(displayedCities, requireContext(), ::fetchCity)

        binding.citiesRv.apply {
            adapter = this@SearchFragment.adapter
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        //Search bar handling
        binding.searchEditText.doOnTextChanged { text, _, _, _ -> changeList(text!!) }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun fetchCity(item: CityJson) {
        RetrofitInstance.OPEN_WEATHER.getByCityId(cityId = item.id, units = "metric")
            .enqueue(object :
                Callback<CityData> {
                override fun onResponse(call: Call<CityData>, response: Response<CityData>) {
                    viewModel.viewModelScope.launch {
                        if (response.body() != null) {
                            viewModel.selected.value = response.body()!!.apply {
                                weather = firstWeather; lastFetched = System.currentTimeMillis()
                            }
                            viewModel.verifyIfInDb()
                        } else
                            viewModel.selected.value = fetchFromDb(item)
                    }
                    findNavController().navigate(R.id.action_searchFragment_to_cityFragment)

                }

                override fun onFailure(call: Call<CityData>, t: Throwable) {
                    viewModel.viewModelScope.launch { viewModel.selected.value = fetchFromDb(item) }
                    findNavController().navigate(R.id.action_searchFragment_to_cityFragment)
                }

            })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun changeList(text: CharSequence) {
        displayedCities.clear()
        displayedCities.addAll(listViewModel.cityList.stream()
            .filter { it.name.contains(text.toString(), ignoreCase = true) }
            .toList())

        adapter.notifyDataSetChanged()
    }

    private suspend fun fetchFromDb(item: CityJson): CityData {
        return WeatherDb.getInstance().cityDao().getCityById(item.id)!!
    }
}



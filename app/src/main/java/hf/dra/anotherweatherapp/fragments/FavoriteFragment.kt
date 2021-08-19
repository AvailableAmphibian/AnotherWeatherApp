package hf.dra.anotherweatherapp.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import hf.dra.anotherweatherapp.R
import hf.dra.anotherweatherapp.adapters.FavoriteCityAdapter
import hf.dra.anotherweatherapp.databinding.FragmentFavoriteBinding
import hf.dra.anotherweatherapp.model.CityData
import hf.dra.anotherweatherapp.view_models.CityViewModel
import hf.dra.anotherweatherapp.view_models.FavoriteCitiesViewModel

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavoriteBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: FavoriteCitiesViewModel by activityViewModels()
    private val cityViewModel: CityViewModel by activityViewModels()

    private lateinit var adapter: FavoriteCityAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFavoriteBinding.inflate(inflater, container, false)

        listViewModel.cities.observe(viewLifecycleOwner) {
            adapter = FavoriteCityAdapter(it, requireContext()) { cityData ->
                Log.i("Favorite", "clicked on item: ${cityData.name}")
                cityViewModel.selected.value = cityData
                findNavController().navigate(R.id.action_favoriteFragment_to_cityFragment)
            }
            binding.list.adapter = adapter
        }

        binding.list.apply {
            layoutManager = LinearLayoutManager(requireContext())
            addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                )
            )
        }

        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getDetails(cityData: CityData) {
        cityViewModel.selected.value = cityData
        findNavController().navigate(R.id.action_favoriteFragment_to_cityFragment)
    }
}
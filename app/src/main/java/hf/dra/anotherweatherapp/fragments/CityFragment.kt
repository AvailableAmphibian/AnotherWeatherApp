package hf.dra.anotherweatherapp.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import hf.dra.anotherweatherapp.databinding.FragmentCityBinding
import hf.dra.anotherweatherapp.view_models.CityViewModel


class CityFragment : Fragment() {
    private var _binding: FragmentCityBinding? = null
    private val binding get() = _binding!!

    private val viewModel: CityViewModel by activityViewModels()

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
}
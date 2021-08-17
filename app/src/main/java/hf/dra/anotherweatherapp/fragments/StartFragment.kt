package hf.dra.anotherweatherapp.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import hf.dra.anotherweatherapp.MainActivity
import hf.dra.anotherweatherapp.R
import hf.dra.anotherweatherapp.databinding.FragmentStartBinding
import hf.dra.anotherweatherapp.view_models.CityListViewModel


class StartFragment : Fragment() {
    private var _binding: FragmentStartBinding? = null
    private val binding get() = _binding!!

    private val listViewModel: CityListViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentStartBinding.inflate(inflater, container, false)

        listViewModel.initCityListJson((requireActivity() as MainActivity).assets)

        return binding.root
    }

    override fun onResume() {
        super.onResume()
        findNavController().navigate(R.id.action_startFragment_to_searchFragment2)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
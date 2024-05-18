package com.example.parking.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.parking.databinding.FragmentCityZonesBinding

class CityZonesFragment: Fragment() {
    lateinit var binding: FragmentCityZonesBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityZonesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.firstZone.viewInMapButton.setOnClickListener {
            navigate()
        }
    }
    fun navigate(){
        val action =
            CityZonesFragmentDirections.actionCityZonesFragmentToMapsFragment()
        findNavController().navigate(action)
    }
}
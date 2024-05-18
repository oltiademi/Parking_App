package com.example.parking.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parking.adapter.VehiclesCarPlatesAdapter
import com.example.parking.databinding.FragmentVehiclesBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class VehiclesFragment : Fragment() {
    lateinit var binding: FragmentVehiclesBinding
    val adapter = VehiclesCarPlatesAdapter(this::deletePlate)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVehiclesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.carPlatesRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.carPlatesRecycler.adapter = adapter

        val myPref: SharedPreferences =
            requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val plates = myPref.getString("plates", "[]")
        val theList = gson.fromJson<ArrayList<String>>(
            plates,
            object : TypeToken<ArrayList<String>>() {}.type
        )
        adapter.plates = theList

        binding.addNewPlatesButton.setOnClickListener {
            val action =
                VehiclesFragmentDirections.actionVehiclesFragmentToFirstTimeUserBottomSheetFragment()
            findNavController().navigate(action)
        }

    }

    fun deletePlate(plate: String){
        val myPref: SharedPreferences =
            requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val plates = myPref.getString("plates", "[]")
        val theList = gson.fromJson<ArrayList<String>>(
            plates,
            object : TypeToken<ArrayList<String>>() {}.type
        )
        theList.removeIf {
            it == plate
        }

        myPref.edit().putString("plates", gson.toJson(theList)).apply()
        adapter.plates = theList
    }
}

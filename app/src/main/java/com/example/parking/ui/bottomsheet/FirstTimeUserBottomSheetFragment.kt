package com.example.parking.ui.bottomsheet

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import com.example.parking.R
import com.example.parking.adapter.BottomSheetPlateAdapter
import com.example.parking.databinding.BottomSheetFirstTimeUserDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class FirstTimeUserBottomSheetFragment:BottomSheetDialogFragment() {
    lateinit var binding : BottomSheetFirstTimeUserDialogBinding
    lateinit var adapter: BottomSheetPlateAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetFirstTimeUserDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = BottomSheetPlateAdapter{}
        binding.savePlatesButton.setOnClickListener {
            binding.savePlatesButton.setOnClickListener {
                val myPref: SharedPreferences =
                    requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
                val gson = Gson()
                val plates = myPref.getString("plates", "[]")
                val theList = gson.fromJson<ArrayList<String>>(
                    plates,
                    object : TypeToken<ArrayList<String>>() {}.type
                )
                theList.add(binding.plateInput.text.toString())
                adapter.plates = theList
                myPref.edit().putString("plates", gson.toJson(theList)).apply()
                val action =
                    FirstTimeUserBottomSheetFragmentDirections.actionFirstTimeUserBottomSheetFragmentToVehiclesFragment()
                findNavController().navigate(action)
            }
        }
    }
}
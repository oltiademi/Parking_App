package com.example.parking.ui.bottomsheet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parking.adapter.BottomSheetPlateAdapter
import com.example.parking.databinding.BottomSheetPlatesDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class BottomSheetPlateFragment : BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetPlatesDialogBinding
    lateinit var adapter: BottomSheetPlateAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetPlatesDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter = BottomSheetPlateAdapter(this::setLastPlateClicked)
        binding.carPlatesRecycler.layoutManager = LinearLayoutManager(requireActivity())
        binding.carPlatesRecycler.adapter = adapter
        binding.addNewPlates.setOnClickListener {
            binding.addNewPlates.visibility = View.GONE
            binding.carPlatesEdittext.visibility = View.VISIBLE
        }

        val myPref: SharedPreferences =
            requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val plates = myPref.getString("plates", "[]")
        val theList = gson.fromJson<ArrayList<String>>(
            plates,
            object : TypeToken<ArrayList<String>>() {}.type
        )
        adapter.plates = theList
        binding.savePlatesButton.setOnClickListener {
            val myPref: SharedPreferences =
                requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
            val gson = Gson()
            val plates = myPref.getString("plates", "[]")
            val theList = gson.fromJson<ArrayList<String>>(
                plates,
                object : TypeToken<ArrayList<String>>() {}.type
            )
            theList.add(binding.platesInput.text.toString())
            adapter.plates = theList
            myPref.edit().putString("plates", gson.toJson(theList)).apply()
            val action =
                BottomSheetPlateFragmentDirections.actionBottomSheetPlateFragmentToPaymentInfoFragment(
                    binding.platesInput.text.toString()
                )
            findNavController().navigate(action)
        }
    }
    fun setLastPlateClicked(plate: String){
        val myPref: SharedPreferences =
            requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        myPref.edit().putString("lastClickedPlate",plate).apply()
        val action =
            BottomSheetPlateFragmentDirections.actionBottomSheetPlateFragmentToPaymentInfoFragment(
                plate
            )
        findNavController().navigate(action)
    }
}
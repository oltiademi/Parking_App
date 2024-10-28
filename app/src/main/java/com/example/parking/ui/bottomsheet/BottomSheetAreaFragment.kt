package com.example.parking.ui.bottomsheet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parking.Area
import com.example.parking.adapter.BottomSheetAreaAdapter
import com.example.parking.databinding.BottomSheetAreaDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.Gson

class BottomSheetAreaFragment : BottomSheetDialogFragment() {
    lateinit var binding : BottomSheetAreaDialogBinding
    val adapter = BottomSheetAreaAdapter(this::setLastAreaClicked)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = BottomSheetAreaDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val gson = Gson()
        val jsonFile = context?.assets?.open("area.json")?.bufferedReader().use { it?.readText() }
        val areasJsonArray = gson.fromJson(jsonFile, Array<Area>::class.java)
        binding.areaRecylcer.layoutManager = LinearLayoutManager(requireActivity())
        binding.areaRecylcer.adapter = adapter
        adapter.area = areasJsonArray.toMutableList()
    }

    fun setLastAreaClicked(area: Area) {
        val myPref: SharedPreferences =
            requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        myPref.edit().putString("lastClickedArea", area.name).apply()
        dismiss()
    }

    }
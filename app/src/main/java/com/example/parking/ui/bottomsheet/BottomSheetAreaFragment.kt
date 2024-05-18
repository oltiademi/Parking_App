package com.example.parking.ui.bottomsheet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parking.adapter.BottomSheetAreaAdapter
import com.example.parking.databinding.BottomSheetAreaDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetAreaFragment : BottomSheetDialogFragment() {
    lateinit var binding : BottomSheetAreaDialogBinding
    lateinit var adapter : BottomSheetAreaAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View {
        binding = BottomSheetAreaDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.areaRecylcer.layoutManager = LinearLayoutManager(requireActivity())
        binding.areaRecylcer.adapter = adapter
    }
}
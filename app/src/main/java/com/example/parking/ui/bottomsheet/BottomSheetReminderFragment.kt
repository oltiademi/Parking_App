package com.example.parking.ui.bottomsheet

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.parking.databinding.BottomSheetReminderDialogBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class BottomSheetReminderFragment: BottomSheetDialogFragment() {
    lateinit var binding: BottomSheetReminderDialogBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = BottomSheetReminderDialogBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.setReminderButton.setOnClickListener {
            val isClicked = true
            val myPref: SharedPreferences =
                requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
            myPref.edit().putBoolean("isClicked", isClicked).apply()
            val action = BottomSheetReminderFragmentDirections.actionBottomSheetReminderFragmentToPaymentInfoFragment("")
            findNavController().navigate(action)
        }
        binding.cancelReminderButton.setOnClickListener {
            val action = BottomSheetReminderFragmentDirections.actionBottomSheetReminderFragmentToPaymentInfoFragment("")
            findNavController().navigate(action)
        }
    }

}
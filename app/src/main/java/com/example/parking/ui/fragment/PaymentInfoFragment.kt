package com.example.parking.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.parking.databinding.FragmentPaymentInfoBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class PaymentInfoFragment : Fragment() {
    private lateinit var binding: FragmentPaymentInfoBinding
    val args: PaymentInfoFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentPaymentInfoBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val myPref: SharedPreferences =
            requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val gson = Gson()
        val plates = myPref.getString("plates", "[]")
        val theList = gson.fromJson<ArrayList<String>>(
            plates,
            object : TypeToken<ArrayList<String>>() {}.type
        )
        binding.plateLayout.penImage.setOnClickListener {
            if(theList ==null){
                val action =
                    PaymentInfoFragmentDirections.actionPaymentInfoFragmentToFirstTimeUserBottomSheetFragment()
                findNavController().navigate(action)
            }else{
                val action =
                   PaymentInfoFragmentDirections.actionPaymentInfoFragmentToBottomSheetPlateFragment()
                findNavController().navigate(action)
            }
        }

        binding.areaLayout.penImage.setOnClickListener {
            val action =
                PaymentInfoFragmentDirections.actionPaymentInfoFragmentToFirstTimeUserBottomSheetFragment()
            findNavController().navigate(action)
        }

        binding.setReminderButton.setOnClickListener {
            val action =
                PaymentInfoFragmentDirections.actionPaymentInfoFragmentToBottomSheetReminderFragment()
            findNavController().navigate(action)
        }
    }

    override fun onResume() {
        super.onResume()
        val myPref: SharedPreferences =
            requireActivity().getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val isReminderSet = myPref.getBoolean("isClicked", false)
        val lastPlate = myPref.getString("lastClickedPlate", "")
        if(lastPlate!!.isEmpty()){
            binding.plateLayout.plateTextview.text = "Select a vehicle plate"
        }else{
            binding.plateLayout.plateTextview.text = lastPlate
        }
        if(isReminderSet){
            startTimer(3600000)
        }
    }

    private fun startTimer(duration: Long) {
        val timer = object : CountDownTimer(duration, 1000) { // 60 minutes in milliseconds
            override fun onTick(millisUntilFinished: Long) {
                val minutes = millisUntilFinished / 60000
                binding.setReminderButton.text = "$minutes minutes until the reminder"
            }

            override fun onFinish() {
                binding.setReminderButton.text = "Set a parking reminder"
            }
        }
        timer.start()
        val minutes = duration / 60000
        binding.setReminderButton.text = "$minutes minutes until the reminder"
    }
}
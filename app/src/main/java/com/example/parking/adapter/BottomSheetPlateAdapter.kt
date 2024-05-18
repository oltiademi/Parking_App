package com.example.parking.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.databinding.BottomSheetPlateItemBinding
import com.google.gson.Gson

class BottomSheetPlateAdapter(val onClick : (String)->Unit) : RecyclerView.Adapter<BottomSheetPlateAdapter.ViewHolder>() {
    var plates = mutableListOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    class ViewHolder(val bottomSheetItemBinding: BottomSheetPlateItemBinding) :
        RecyclerView.ViewHolder(bottomSheetItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BottomSheetPlateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = plates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plate = plates[position]
        holder.bottomSheetItemBinding.plateText.text = plate

        holder.itemView.setOnClickListener {
            onClick(plate)
            holder.bottomSheetItemBinding.usedLastTime.visibility = View.VISIBLE
        }
    }

}
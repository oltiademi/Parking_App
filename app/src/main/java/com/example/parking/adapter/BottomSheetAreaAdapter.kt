package com.example.parking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.databinding.BottomSheetAreaItemBinding

class BottomSheetAreaAdapter() : RecyclerView.Adapter<BottomSheetAreaAdapter.ViewHolder>() {
    var area = mutableListOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class ViewHolder(val bottomSheetItemBinding: BottomSheetAreaItemBinding) :
        RecyclerView.ViewHolder(bottomSheetItemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = BottomSheetAreaItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = area.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val area = area[position]
        with(holder.bottomSheetItemBinding){
            areaName.text = area
        }
    }

}
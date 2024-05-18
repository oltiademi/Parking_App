package com.example.parking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.databinding.CarPlateItemBinding

class VehiclesCarPlatesAdapter(val onClick: (String)->Unit): RecyclerView.Adapter<VehiclesCarPlatesAdapter.ViewHolder>() {
    var plates = mutableListOf<String>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }
    class ViewHolder(var binding: CarPlateItemBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CarPlateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = plates.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val plate = plates[position]
        with(holder.binding){
            plateTextview.text = plate
        }
        holder.binding.deletePlateButton.setOnClickListener {
            onClick(plate)
        }
    }


}
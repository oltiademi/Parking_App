package com.example.parking.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.parking.Location
import com.example.parking.databinding.LocationsListItemBinding

class LocationsAdapter(private val locations: List<Location>):RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {
    class ViewHolder(val binding : LocationsListItemBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = LocationsListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = locations.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val location = locations[position]
        holder.binding.locationName.text = location.locationName
    }
}
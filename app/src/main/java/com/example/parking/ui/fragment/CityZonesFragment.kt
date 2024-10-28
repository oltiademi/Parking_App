package com.example.parking.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.parking.Area
import com.example.parking.adapter.LocationsAdapter
import com.example.parking.databinding.CityZonesLayoutBinding
import com.example.parking.databinding.FragmentCityZonesBinding
import com.google.gson.Gson
import org.osmdroid.util.GeoPoint

class CityZonesFragment : Fragment() {
    private lateinit var binding: FragmentCityZonesBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentCityZonesBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val gson = Gson()
        val jsonFile = context?.assets?.open("area.json")?.bufferedReader().use { it?.readText() }
        val areasJsonArray = gson.fromJson(jsonFile, Array<Area>::class.java)

        areasJsonArray.forEachIndexed { index, area ->
            val zoneBinding = when (index) {
                0 -> binding.firstZone
                1 -> binding.secondZone
                3->binding.thirdZone
                else -> binding.fourthZone
            }
            populateLayout(zoneBinding, area)

            zoneBinding.viewInMapButton.setOnClickListener {
                val geoPoints = ArrayList<GeoPoint>()
                area.locations.forEach { location ->
                    val latitude = location.geoPoints[0]
                    val longitude =
                        location.geoPoints[1]
                    geoPoints.add(GeoPoint(latitude, longitude))
                }
                navigate(geoPoints)
            }
        }
    }


    private fun navigate(geoPoints: ArrayList<GeoPoint>) {
        val floatArray =
            geoPoints.flatMap { listOf(it.latitude.toFloat(), it.longitude.toFloat()) }
                .toFloatArray()

        val action = CityZonesFragmentDirections.actionCityZonesFragmentToMapsFragment(floatArray)
        findNavController().navigate(action)
    }

    private fun populateLayout(layout: CityZonesLayoutBinding, area: Area) {
        layout.areaNumberText.text = area.name
        layout.priceValueTextview.text = "${area.price} EUR/hr"
        val layoutManager =
            LinearLayoutManager(requireActivity(), LinearLayoutManager.HORIZONTAL, false)
        layout.locations.layoutManager = layoutManager
        layout.locations.adapter = LocationsAdapter(area.locations)
    }
}

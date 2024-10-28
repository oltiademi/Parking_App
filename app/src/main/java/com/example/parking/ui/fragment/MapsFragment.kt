package com.example.parking.ui.fragment

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.example.parking.databinding.FragmentMapsBinding
import com.example.parking.overlay.AreaOverlay
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint

class MapsFragment : Fragment() {
    private lateinit var binding: FragmentMapsBinding
    val args: MapsFragmentArgs by navArgs()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        Configuration.getInstance().load(requireContext(), PreferenceManager.getDefaultSharedPreferences(context))
        binding = FragmentMapsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapController = binding.map.controller
        binding.map.setTileSource(TileSourceFactory.MAPNIK)
        binding.map.setMultiTouchControls(true)

        val geoPointsArray = args.geoPoints
        val geoPoints = ArrayList<GeoPoint>()

        for (i in geoPointsArray.indices step 2) {
            val latitude = geoPointsArray[i].toDouble()
            val longitude = geoPointsArray[i + 1].toDouble()
            val geoPoint = GeoPoint(latitude, longitude)
            geoPoints.add(geoPoint)
        }

        val areaOverlay = AreaOverlay(geoPoints)
        binding.map.overlayManager.add(areaOverlay)

        mapController.setZoom(15.0)
        if (geoPoints.isNotEmpty()) {
            val averageLatitude = geoPoints.map { it.latitude }.average()
            val averageLongitude = geoPoints.map { it.longitude }.average()
            val centerPoint = GeoPoint(averageLatitude, averageLongitude)
            mapController.setCenter(centerPoint)
        }
    }

}

package com.example.parking.ui.fragment

import android.os.Bundle
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.parking.databinding.FragmentMapsBinding
import com.example.parking.overlay.AreaOverlay
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint

class MapsFragment : Fragment() {
    lateinit var binding: FragmentMapsBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Configuration.getInstance().load(
            context, PreferenceManager.getDefaultSharedPreferences(context)
        )
        binding = FragmentMapsBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val mapController = binding.map.controller
        binding.map.setTileSource(TileSourceFactory.MAPNIK)
        binding.map.setMultiTouchControls(true)

        val areaCoordinates = listOf(
            GeoPoint(42.6524, 21.1756), //bregu
            GeoPoint(42.6505, 21.1608), //ulpiana
            GeoPoint(42.6432, 21.1435), //kalabria
            GeoPoint(42.6685, 21.1611), //tophane
        )

        val areaOverlay = AreaOverlay(areaCoordinates)

        binding.map.overlayManager.add(areaOverlay)
        mapController.setZoom(15.5)
        val startPoint = GeoPoint(42.6518, 21.1562)
        mapController.setCenter(startPoint)
    }
}
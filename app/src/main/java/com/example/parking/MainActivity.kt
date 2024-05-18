package com.example.parking

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.view.isVisible
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.parking.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val whiteZonesIcon = AppCompatResources.getDrawable(this, R.drawable.ic_zones_white)
        val blueZonesIcon = AppCompatResources.getDrawable(this, R.drawable.ic_zones_blue)
        val blueCarIcon = AppCompatResources.getDrawable(this, R.drawable.ic_car_blue)
        val whiteCarIcon = AppCompatResources.getDrawable(this, R.drawable.ic_car_white)

        val navHostFragment = supportFragmentManager
            .findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController


        binding.zonesButton.setOnClickListener {
            navController.navigate(R.id.cityZonesFragment)
        }
        binding.vehiclesButton.setOnClickListener {
            navController.navigate(R.id.vehiclesFragment)
        }
        binding.goBackButton.setOnClickListener {
            navController.popBackStack()
        }

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.cityZonesFragment->{
                    binding.logo.isVisible = false
                    binding.goBackButton.isVisible = true
                    binding.zonesButton.setCompoundDrawablesWithIntrinsicBounds(null, whiteZonesIcon, null, null)
                    binding.vehiclesButton.setCompoundDrawablesWithIntrinsicBounds(null,blueCarIcon, null ,null )

                }
                R.id.vehiclesFragment->{
                    binding.logo.isVisible = false
                    binding.goBackButton.isVisible = true
                    binding.zonesButton.setCompoundDrawablesWithIntrinsicBounds(null, blueZonesIcon, null, null)
                    binding.vehiclesButton.setCompoundDrawablesWithIntrinsicBounds(null, whiteCarIcon, null, null)
                }
                R.id.mapsFragment->{
                    binding.logo.isVisible = false
                    binding.goBackButton.isVisible = true
                    binding.zonesButton.setCompoundDrawablesWithIntrinsicBounds(null, whiteZonesIcon, null, null)
                    binding.vehiclesButton.setCompoundDrawablesWithIntrinsicBounds(null,blueCarIcon, null ,null )

                }
                else -> {
                    binding.logo.isVisible = true
                    binding.goBackButton.isVisible = false
                    binding.zonesButton.setCompoundDrawablesWithIntrinsicBounds(null,blueZonesIcon, null ,null )
                    binding.vehiclesButton.setCompoundDrawablesWithIntrinsicBounds(null,blueCarIcon, null ,null )
                }
            }
        }

    }
}
package com.example.parking

data class Area(
    val name: String,
    val locations: ArrayList<Location>,
    val price: Double
)

data class Location(
    val locationName: String,
    val geoPoints : ArrayList<Double>
)
package com.example.huwerk

import java.io.Serializable

data class Listing(
    val title: String,
    val description: String,
    val price: Double,
    val location: String,
    val imageUrl: String
) : Serializable

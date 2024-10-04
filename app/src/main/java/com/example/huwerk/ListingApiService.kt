package com.example.huwerk

import retrofit2.Call
import retrofit2.http.GET

interface ListingApiService {
    @GET("api/listings")
    fun getListings(): Call<List<Listing>>
}

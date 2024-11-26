package com.example.pokedex.services.endpoints

import com.example.pokedex.services.models.RegionResponse
import retrofit2.Response
import retrofit2.http.GET

interface RegionesEndpoints {
    @GET("pokedex")
    suspend fun getAllRegiones(): Response<RegionResponse>
}
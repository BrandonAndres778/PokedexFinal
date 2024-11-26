package com.example.pokedex.services.endpoints

import com.example.pokedex.services.models.PokedexResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface ListaPokemonEndpoint {
    @GET("pokedex/{regionName}")
    suspend fun getPokemonsByRegion(@Path("regionName") regionName: String): Response<PokedexResponse>

}
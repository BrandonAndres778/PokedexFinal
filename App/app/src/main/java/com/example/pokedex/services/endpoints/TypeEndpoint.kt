package com.example.pokedex.services.endpoints

import TypeResponse
import com.example.pokedex.services.models.TypeDetails
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeEndpoint {
    @GET("type/")
    suspend fun getPokemonTypes(): Response<TypeResponse>

    @GET("type/{tipo}")
    suspend fun  getPokemonsByType(@Path("tipo") tipo: String): Response<TypeDetails>
}
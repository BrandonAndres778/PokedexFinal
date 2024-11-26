package com.example.pokedex.services.endpoints

import com.example.pokedex.services.models.EvolutionChainResponse
import com.example.pokedex.services.models.Pokemon
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonEndpoint {
    @GET("pokemon/{pokemon_name}")
    suspend fun getPokemonDetails(@Path("pokemon_name") pokemonName: String): Response<Pokemon>
}
package com.example.pokedex.services.endpoints

import com.example.pokedex.services.models.EvolutionChainResponse
import com.example.pokedex.services.models.PokemonSpecies
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface EvolutionChainEndpoint {
    @GET("evolution-chain/{evolutionChainID}/")
    suspend fun getEvolutionChain(@Path("evolutionChainID") evolutionChainID: Int): Response<EvolutionChainResponse>

    @GET("pokemon-species/{Pokemon_id}/")
    suspend fun getSpeciesEvolution(@Path("Pokemon_id") Pokemon_id: Int): Response<PokemonSpecies>
}
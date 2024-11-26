package com.example.pokedex.services.controllers

import androidx.lifecycle.viewModelScope
import com.example.pokedex.services.endpoints.EvolutionChainEndpoint
import com.example.pokedex.services.models.EvolutionChain
import com.example.pokedex.services.models.PokemonSpecies
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EvolutionChainService : BaseService() {

    fun getEvolutionChainById(
        evolutionChainID: Int,
        success: (evolutionChain: EvolutionChain) -> Unit,
        error: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRetrofit()
                    .create(EvolutionChainEndpoint::class.java)
                    .getEvolutionChain(evolutionChainID)

                val data = response.body()

                if (response.isSuccessful && data != null) {
                    val evolutionChain = data.chain
                    success(evolutionChain)
                } else {
                    println("Error en la respuesta: ${response.code()}")
                    error()
                }
            } catch (e: Exception) {
                println("Error en la conexión: ${e.message}")
                error()
            }
        }
    }
    fun getPokemonEvolution(
        Pokemon_id: Int,
        success: (pokemonSpecies: PokemonSpecies) -> Unit,
        error: () -> Unit
    ){
        println("Pokemon id en evolution chain ${Pokemon_id}")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRetrofit()
                    .create(EvolutionChainEndpoint::class.java)
                    .getSpeciesEvolution(Pokemon_id)

                val data = response.body()

                if (response.isSuccessful && data != null){
                    val evolutionChainID = data
                    println("evolutionChainID ${evolutionChainID}")
                    success(evolutionChainID)
                } else{
                    println("Error en la respuesta: ${response.code()}")
                    error()
                }
            } catch (e: Exception){
                println("Error en la conexión evolutionID: ${e.message}")
            }
        }
    }
}
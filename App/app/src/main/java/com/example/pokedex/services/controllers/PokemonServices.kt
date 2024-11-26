package com.example.pokedex.services.controllers

import androidx.lifecycle.viewModelScope
import com.example.pokedex.services.endpoints.PokemonEndpoint
import com.example.pokedex.services.models.Pokemon
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class PokemonServices : BaseService() {

    fun getPokemonDetails(
        pokemonName: String,
        success: (details: Pokemon) -> Unit,
        error: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRetrofit()
                    .create(PokemonEndpoint::class.java)
                    .getPokemonDetails(pokemonName)

                val data = response.body()
                if (response.isSuccessful && data != null) {
                    val pokemonConImg = data.copy(
                        imgUrl = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${data.id}.png"
                    )
                    success(pokemonConImg)
                } else {
                    println("Error en la respuesta de la API: ${response.errorBody()?.string()}")
                    error()
                }
            } catch (e: Exception) {
                println(e)
                error()
            }
        }
    }


}
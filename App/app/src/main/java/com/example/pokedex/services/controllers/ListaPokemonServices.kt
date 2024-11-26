package com.example.pokedex.services.controllers

import androidx.lifecycle.viewModelScope
import com.example.pokedex.services.endpoints.ListaPokemonEndpoint
import com.example.pokedex.services.models.PokemonEntry
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ListaPokemonServices : BaseService() {

    fun getPokemonsByRegion(
        regionName: String,
        success: (pokemonList: List<PokemonEntry>) -> Unit,
        error: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRetrofit()
                    .create(ListaPokemonEndpoint::class.java)
                    .getPokemonsByRegion(regionName)
                val data = response.body()
                if (response.isSuccessful && data != null) {
                    val pokemonList = data.pokemon_entries.map { entry ->
                        entry.copy(
                            image_url = generateSpriteUrl(entry.pokemon_species.url)
                        )
                    }
                    println("La lista de pokemons ${pokemonList}")
                    success(pokemonList)
                } else {
                    success(emptyList())
                }
            } catch (e: Exception) {
                println(e)
                error()
            }
        }
    }

    private fun generateSpriteUrl(Url: String): String {
        return "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/other/official-artwork/${Url.split("/").dropLast(1).last()}.png"
    }

}

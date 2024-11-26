package com.example.pokedex.services.driverAdapters

import com.example.pokedex.services.controllers.PokemonServices
import com.example.pokedex.services.models.Pokemon

class PokemonDiverAdapter {
    private val service: PokemonServices = PokemonServices()

    fun getPokemonDetails(
        pokemonName: String,
        loadData: (Pokemon) -> Unit,
        errorData: () -> Unit
    ) {
        this.service.getPokemonDetails(
            pokemonName = pokemonName,
            success = { loadData(it) },
            error = { errorData() }
        )
    }

}
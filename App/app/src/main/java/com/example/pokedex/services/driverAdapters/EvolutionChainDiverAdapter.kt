package com.example.pokedex.services.driverAdapters

import com.example.pokedex.services.controllers.EvolutionChainService
import com.example.pokedex.services.models.EvolutionChain
import com.example.pokedex.services.models.PokemonSpecies

class EvolutionChainDiverAdapter {
    private val service: EvolutionChainService = EvolutionChainService()

    fun getEvolutionChain(
        evolutionChainID: Int,
        loadData: (evolutionChain: EvolutionChain) -> Unit,
        errorData: () -> Unit
    ) {
        this.service.getEvolutionChainById(
            evolutionChainID = evolutionChainID,
            success = { loadData(it) },
            error = { errorData() }
        )
    }

    fun getPokemonEvolution(
        Pokemon_id: Int,
        loadData: (pokemonSpecies: PokemonSpecies) -> Unit,
        errorData: () -> Unit
    ){
        this.service.getPokemonEvolution(
            Pokemon_id = Pokemon_id,
            success = {loadData(it)},
            error = {errorData()}
        )
    }
}
package com.example.pokedex.services.driverAdapters

import com.example.pokedex.services.controllers.ListaPokemonServices
import com.example.pokedex.services.models.PokemonEntry


class ListaPokemonDiverAdapter {
    private val service: ListaPokemonServices = ListaPokemonServices()

    fun allPokemonsByRegion(
        regionName: String,
        loadData: (list: List<PokemonEntry>) -> Unit,
        errorData: () -> Unit
    ) {
        this.service.getPokemonsByRegion(
            regionName = regionName,
            success = { loadData(it) },
            error = { errorData() }
        )
    }

}
package com.example.pokedex.services.models

data class EvolutionChainResponse(
    val id: Int,
    val baby_trigger_item: Any?, // Puede ser un objeto o nulo
    val chain: EvolutionChain
)

data class EvolutionChain(
    val species: EvolutionSpecies,
    val evolves_to: List<EvolutionChain>
)

data class EvolutionSpecies(
    val name: String,
    val url: String
)

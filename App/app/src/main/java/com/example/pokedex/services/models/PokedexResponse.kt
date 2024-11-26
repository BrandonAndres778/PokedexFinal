package com.example.pokedex.services.models

data class PokedexResponse(
    val pokemon_entries: List<PokemonEntry>
)

data class PokemonEntry(
    val entry_number: Int,
    val pokemon_species: PokemonSpecies,
    val image_url: String,
)

data class PokemonSpecies(
    val pokemon: Pokemon,
    val name: String,
    val url: String,
    val evolution_chain: evolutionChainUrl
)

data class evolutionChainUrl(
    val url: String
)


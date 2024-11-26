package com.example.pokedex.services.models

data class TypeDetails(
    val id: Int,
    val name: String,
    val pokemon: List<TypePokemon>
)


data class TypePokemon(
    val pokemon: pokemonType,
    val slot: Int
)

data class pokemonType(
    val name: String,
    val url: String
)
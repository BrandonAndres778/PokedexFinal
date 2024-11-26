package com.example.pokedex.services.models

data class Pokemon(
    val id: Int,
    val name: String,
    val height: Int,
    val weight: Int,
    val types: List<TypeEntry>,
    val abilities: List<AbilityEntry>,
    val stats: List<Stat>,           // Estadísticas del pokémon
    val imgUrl: String
)

data class TypeEntry(
    val type: Type
)

data class Type(
    val name: String
)

data class Stat(
    val base_stat: Int,
    val effort: Int,
    val stat: NamedAPIResource
)

data class NamedAPIResource(
    val name: String,
    val url: String
)

data class AbilityEntry(
    val ability: Ability
)

data class Ability(
    val name: String
)

package com.example.pokedex.services.models

data class RegionResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<Region>
)

data class Region(
    var name: String,
    var url: String
)
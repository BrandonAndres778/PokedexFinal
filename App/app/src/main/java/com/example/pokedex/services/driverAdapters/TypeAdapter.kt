package com.example.pokedex.services.driverAdapters

import TypeFiltro
import com.example.pokedex.services.controllers.TypeService
import com.example.pokedex.services.models.TypePokemon


class TypeAdapter {
    private val service = TypeService()

    fun getAllTypes(
        loadData: (list: List<TypeFiltro>) -> Unit,
        errorData: () -> Unit
    ) {
        service.getPokemonTypes(
            success = { loadData(it) },
            error = { errorData() }
        )
    }

    fun getPokemonsByType(
        tipo: String,
        loadData: (List<TypePokemon>) -> Unit,
        errorData: () -> Unit
    ) {
            service.getPokemonsByType(
                tipo = tipo,
                success = { loadData(it)  },
                error = { errorData()  }
            )
        }
    }

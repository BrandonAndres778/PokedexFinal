package com.example.pokedex.dataBases.repositories

import com.example.pokedex.dataBases.Entities.PokemonEntity
import com.example.pokedex.dataBases.daos.pokemonsDao
import kotlinx.coroutines.flow.Flow


class PokemonsRepository(private val dao: pokemonsDao){
    fun getAll(): Flow<List<PokemonEntity>>{
        return this.dao.getAll()
    }

    fun find(id: Int): Flow<PokemonEntity>{
        return this.dao.find(id)
    }


    suspend fun save(pokemon: PokemonEntity){
        return this.dao.save(pokemon)
    }

    suspend fun delete(id: Int) {
        dao.delete(id)
    }
}
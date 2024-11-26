package com.example.pokedex.dataBases.daos

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.pokedex.dataBases.Entities.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface pokemonsDao {
    @Query("select * from pokemons")
    fun getAll(): Flow<List<PokemonEntity>>

    @Query("select * from pokemons where id=:id")
    fun find(id: Int): Flow<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun save(vararg pokemon: PokemonEntity)

    @Query("DELETE FROM pokemons WHERE id = :id")
    suspend fun delete(id: Int)


}
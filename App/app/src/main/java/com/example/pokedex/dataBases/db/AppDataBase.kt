package com.example.pokedex.dataBases.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.pokedex.dataBases.Entities.PokemonEntity
import com.example.pokedex.dataBases.daos.pokemonsDao


@Database(
    entities = [
        PokemonEntity::class
    ],
    version = 5
)

abstract class AppDataBase: RoomDatabase() {
    abstract fun pokemonsDao(): pokemonsDao
}
package com.example.pokedex.dataBases.viewsModels

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.pokedex.dataBases.ConexionDB
import com.example.pokedex.dataBases.Entities.PokemonEntity
import com.example.pokedex.dataBases.daos.pokemonsDao
import com.example.pokedex.dataBases.repositories.PokemonsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class PokemonFavoritos(val context: Context) : ViewModel(){
    private val repository: PokemonsRepository

    init {
        val dao = ConexionDB.getDataBase(context).pokemonsDao()
        this.repository = PokemonsRepository(dao)
    }

    fun loadPokemons(
        data: (pokemons: List<PokemonEntity>) -> Unit){
        viewModelScope.launch(Dispatchers.Main){
            repository.getAll().collect{
                data(it)
            }
        }
    }

    fun searchPokemon(id: Int, result: (pokemon:PokemonEntity)->Unit){
        viewModelScope.launch(Dispatchers.Main){
            repository.find(id).collect{
                result(it)
            }
        }
    }


    fun savePokemonFavorito(pokemon: PokemonEntity){
        viewModelScope.launch (Dispatchers.Main){
            repository.save(pokemon)
        }
    }

    fun deletePokemonFavorito(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.delete(id)
        }
    }

}

package com.example.pokedex.services.controllers

import androidx.lifecycle.viewModelScope
import com.example.pokedex.services.endpoints.RegionesEndpoints
import com.example.pokedex.services.models.Region
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class RegionesServices : BaseService() {

    fun getAllRegiones(
        success: (regiones: List<Region>) -> Unit,
        error: () -> Unit
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val response = getRetrofit()
                    .create(RegionesEndpoints::class.java)
                    .getAllRegiones()

                val data = response.body()
                if (response.isSuccessful && data != null) {
                    success(data.results)
                } else {
                    success(emptyList())
                }
            } catch (e: Exception) {
                println(e)
                error()
            }
        }
    }
}
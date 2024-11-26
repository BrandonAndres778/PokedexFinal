package com.example.pokedex.services.driverAdapters

import com.example.pokedex.services.controllers.RegionesServices
import com.example.pokedex.services.models.Region

class RegionesDiverAdapter {
    private val service: RegionesServices = RegionesServices()

    fun allRegiones(
        loadData: (list: List<Region>) -> Unit,
        errorData: () -> Unit
    ){
        this.service.getAllRegiones(
            success = {loadData(it)},
            error = {errorData()}
        )
    }
}
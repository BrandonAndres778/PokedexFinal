package com.example.pokedex.services.controllers

import androidx.lifecycle.ViewModel
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

abstract class BaseService: ViewModel() {
    private val URL_DOMAIN: String = "https://pokeapi.co/api/v2/"

    fun getRetrofit(): Retrofit {
        return Retrofit.Builder().baseUrl(this.URL_DOMAIN)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
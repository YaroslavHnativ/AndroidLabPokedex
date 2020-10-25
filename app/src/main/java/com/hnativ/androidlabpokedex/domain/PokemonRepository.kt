package com.hnativ.androidlabpokedex.domain

import androidx.lifecycle.LiveData

interface PokemonRepository {

    val pokemonList: LiveData<List<Pokemon>>

    suspend fun refreshPokemonData()
    suspend fun getPokemonById(id: String): PokemonDetails
}
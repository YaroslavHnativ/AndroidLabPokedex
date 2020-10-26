package com.hnativ.androidlabpokedex.domain

interface PokemonRepository {

    suspend fun getPokemonList(): List<Pokemon>
    suspend fun refreshPokemonData()
    suspend fun getPokemonById(id: String): PokemonDetails
}
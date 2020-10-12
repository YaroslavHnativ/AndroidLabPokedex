package com.hnativ.androidlabpokedex.data

import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import com.hnativ.androidlabpokedex.domain.pokemonList

class PokemonRepositoryImpl: PokemonRepository {

    override fun getPokemonList(): List<Pokemon> {
        return pokemonList
    }
}
package com.hnativ.androidlabpokedex.domain

import com.hnativ.androidlabpokedex.domain.Pokemon

interface PokemonRepository {
    fun getPokemonList(): List<Pokemon>
}
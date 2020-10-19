package com.hnativ.androidlabpokedex.presentation

interface Router {
    fun openPokemonList()
    fun openPokemonDetails(id: String)
}
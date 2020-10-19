package com.hnativ.androidlabpokedex.domain

data class PokemonDetails(
    val id: String,
    val name: String,
    val imgUrl: String,
    val abilities: List<String>
)

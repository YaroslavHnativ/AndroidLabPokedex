package com.hnativ.androidlabpokedex.persistance

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.domain.PokemonDetails

@Entity
data class DatabasePokemonData(
    @PrimaryKey val id: String,
    val name: String,
    val imgUrl: String,
    val height: Int,
    val weight: Int,
)

fun DatabasePokemonData.asPokemonDetails(): PokemonDetails {
    return PokemonDetails(
        id,
        name,
        imgUrl,
        height,
        weight)
}

fun List<DatabasePokemonData>.asPokemon(): List<Pokemon> {
    return map {
        Pokemon(
            it.id,
            it.name,
            it.imgUrl
        )
    }
}
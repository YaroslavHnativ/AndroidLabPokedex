package com.hnativ.androidlabpokedex.data

data class PokemonInfoResponse(
    val id: String,
    val name: String,
    val height: Int,
    val weight: Int,
    val abilities: List<Ability>
) {
    data class Ability(
        val ability: AbilityPartialInfo,
        val slot: Int
    )

    data class AbilityPartialInfo(
        val name: String,
        val url: String
    )
}

val PokemonInfoResponse.imageUrl: String
    get() = "https://pokeres.bastionbot.org/images/pokemon/$id.png"
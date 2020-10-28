package com.hnativ.androidlabpokedex.data

import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import com.hnativ.androidlabpokedex.persistance.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
    private val api: PokedexApiService, private val database: AppDatabase
) : PokemonRepository {

    override suspend fun getPokemonList(): List<Pokemon> {
        return withContext(Dispatchers.IO) {
            database.pokemonDataDao().getPokemonList().asPokemon()
        }
    }

    override suspend fun refreshPokemonData() {
        withContext(Dispatchers.IO) {
            val pokemonListResponse = api.fetchPokemonList()
            pokemonListResponse.results.map { pokemonPartialInfo ->
                val pokemonInfoResponse = api.fetchPokemonInfo(pokemonPartialInfo.name)

                val pokemonData = DatabasePokemonData(
                    pokemonInfoResponse.id,
                    pokemonInfoResponse.name,
                    pokemonInfoResponse.imageUrl,
                    pokemonInfoResponse.height,
                    pokemonInfoResponse.weight
                )

                database.pokemonDataDao().insertPokemonData(pokemonData)
            }
        }
    }

    override suspend fun getPokemonById(id: String): PokemonDetails {
        return withContext(Dispatchers.IO) {
            val pokemonData = database.pokemonDataDao().getPokemonById(id)
            pokemonData.asPokemonDetails()
        }
    }
}
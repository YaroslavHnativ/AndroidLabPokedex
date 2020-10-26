package com.hnativ.androidlabpokedex.data

import com.hnativ.androidlabpokedex.domain.Pokemon
import com.hnativ.androidlabpokedex.domain.PokemonDetails
import com.hnativ.androidlabpokedex.domain.PokemonRepository
import com.hnativ.androidlabpokedex.persistance.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class PokemonRepositoryImpl(
//    private val api: PokedexApiService, private val database: AppDatabase
    private val database: AppDatabase
) : PokemonRepository {

    private val api: PokedexApiService = createPokedexApiService()

    override suspend fun getPokemonList(): List<Pokemon> {
        lateinit var pokemonList: List<Pokemon>
        withContext(Dispatchers.IO) {
            pokemonList = database.pokemonDataDao().getPokemonList().asPokemon()
        }
        return pokemonList
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
        lateinit var pokemonDetails: PokemonDetails
        withContext(Dispatchers.IO) {
            val pokemonData = database.pokemonDataDao().getPokemonById(id)
            pokemonDetails = pokemonData.asPokemonDetails()
        }
        return pokemonDetails
    }
}